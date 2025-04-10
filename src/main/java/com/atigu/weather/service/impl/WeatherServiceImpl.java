package com.atigu.weather.service.impl;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.atigu.weather.pojo.WeatherResponse;
import com.atigu.weather.service.WeatherService;

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

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${weather.url}")
    private String weatherUrl;
    @Value("${weather.kid}")
    private String weatherKid;
    @Value("${weather.privateKey}")
    private String weatherPrivateKey;
    @Value("${weather.projectId}")
    private String weatherProjectId;
    @Override
    public void getWeather()  throws Exception{
        // Private key
        String privateKeyString = weatherPrivateKey;
        privateKeyString = privateKeyString.trim().trim();
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
        PKCS8EncodedKeySpec encoded = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = new EdDSAPrivateKey(encoded);

        // Header
        String headerJson = "{\"alg\": \"EdDSA\", \"kid\":"+weatherKid+"}";

        // Payload
        long iat = ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond() - 30;
        long exp = iat + 9000;
        String payloadJson = "{\"sub\": "+weatherProjectId+", \"iat\": " + iat + ", \"exp\": " + exp + "}";

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


        // API URL
        String url = weatherUrl+"/v7/weather/3d?location=101010100";

        // 发送 GET 请求
        HttpResponse response = HttpUtil.createGet(url)
                .header("Authorization", "Bearer "+jwt) // 添加授权头
                .header("Accept-Encoding", "gzip, deflate, br") // 启用压缩（可选）
                .timeout(20000) // 设置超时时间（毫秒）
                .execute();

        // 输出响应状态码
        System.out.println("Response Code: " + response.getStatus());

        // 输出响应体
        System.out.println("Response Body: " + response.body());
        WeatherResponse weatherResponse = JSONUtil.toBean(response.body(), WeatherResponse.class);

        System.out.println(weatherResponse);
    }
}
