package com.atigu.weather.service.impl;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.atigu.weather.model.AirQualityResponse;
import com.atigu.weather.model.WeatherResponse;
import com.atigu.weather.pojo.Daily;
import com.atigu.weather.service.ThirdService;
import net.i2p.crypto.eddsa.EdDSAEngine;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable;
import net.i2p.crypto.eddsa.spec.EdDSAParameterSpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.List;

/**
 * @BelongsProject: weather
 * @BelongsPackage: com.atigu.weather.service.impl
 * @ClassName ThirdServiceImpl
 * @Author: Utopia
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class ThirdServiceImpl implements ThirdService {

    @Value("${weather.url}")
    private String weatherUrl;
    @Value("${weather.kid}")
    private String weatherKid;
    @Value("${weather.privateKey}")
    private String weatherPrivateKey;
    @Value("${weather.projectId}")
    private String weatherProjectId;

    private final int CONNECTIONTIMEOUT = 50000;

    public String getToken() throws Exception {
        // Private key
        String privateKeyString = weatherPrivateKey;
        privateKeyString = privateKeyString.trim().trim();
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
        PKCS8EncodedKeySpec encoded = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = new EdDSAPrivateKey(encoded);

        // Header
        JSONObject headerJsonO = new JSONObject();
        headerJsonO.put("alg", "EdDSA");
        headerJsonO.put("kid", weatherKid);
        String headerJson =headerJsonO.toString();

        // Payload
        long iat = ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond() - 30;
        long exp = iat + 9000;
        JSONObject payload = new JSONObject();
        payload.put("sub",weatherProjectId);
        payload.put("iat", iat);
        payload.put("exp", exp);
        String payloadJson =payload.toString();

        // Base64url header+payload
        String headerEncoded = Base64.getUrlEncoder().encodeToString(headerJson.getBytes(StandardCharsets.UTF_8));
        String payloadEncoded = Base64.getUrlEncoder().encodeToString(payloadJson.getBytes(StandardCharsets.UTF_8));
        String data = headerEncoded + "." + payloadEncoded;

        EdDSAParameterSpec spec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519);

        // Sign
        final Signature s = new EdDSAEngine(MessageDigest.getInstance(spec.getHashAlgorithm()));
        s.initSign(privateKey);
        s.update(data.getBytes(StandardCharsets.UTF_8));
        byte[] signature = s.sign();

        String signatureString = Base64.getUrlEncoder().encodeToString(signature);

        System.out.println("Signature: \n" + signatureString);



        // Print Token
        String jwt = data + "." + signatureString;

        return jwt;
    }

    @Override
    public List<Daily> getDayWeather(String location) throws Exception {
        String jwt=getToken();
        String url=weatherUrl+"/v7/weather";
        url+="/30d?location="+location;

        // API URL
//        String url = weatherUrl+"/v7/weather/30d?location=120.03,30.29";

        String result = thirdExecute(url, 20000, jwt);
        WeatherResponse weatherResponse = JSONUtil.toBean(result, WeatherResponse.class);
        if ("200".equals(weatherResponse.getCode())){
            List<Daily> daily = weatherResponse.getDaily();
            return daily;
        }else {
            return null;
        }
    }

    @Override
    public AirQualityResponse fetchAndSaveAirQualityData() throws Exception {
        String jwt=getToken();
        String url=weatherUrl+"/airquality/v1/hourly/30.29/120.03";
        String result = thirdExecute(url, 20000, jwt);
        AirQualityResponse airQualityResponse = JSONUtil.toBean(result, AirQualityResponse.class);
        return airQualityResponse;


    }

    public String thirdExecute(String url, int readTimeOutMillis, String jwt) throws Exception {
        String result="";
        try{
            HttpResponse response = HttpUtil.createGet(url).setConnectionTimeout(CONNECTIONTIMEOUT).setReadTimeout(readTimeOutMillis)
                    .header("Authorization", "Bearer " + jwt) // 添加授权头
                    .header("Accept-Encoding", "gzip, deflate, br") // 启用压缩（可选）
                    .execute();
            result=response.body();
            return result;
        }catch (HttpException e){
            e.printStackTrace();
        }finally {
            return result;
        }

    }
}
