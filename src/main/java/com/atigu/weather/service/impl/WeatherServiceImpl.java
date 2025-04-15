package com.atigu.weather.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.atigu.weather.model.WeatherRequest;
import com.atigu.weather.pojo.Daily;
import com.atigu.weather.model.WeatherResponse;
import com.atigu.weather.service.DailyService;
import com.atigu.weather.service.ThirdService;
import com.atigu.weather.service.WeatherService;

import net.i2p.crypto.eddsa.EdDSAEngine;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable;
import net.i2p.crypto.eddsa.spec.EdDSAParameterSpec;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DailyService dailyService;

    @Autowired
    private ThirdService thirdService;

    /**
     *
     * @param request
     * @throws Exception
     */
    @Override
    public void getWeather(WeatherRequest request )  throws Exception{

        String location=request.getLongitude()+","+request.getLatitude();
        List<Daily> dailies = thirdService.getDayWeather(location);
        if (CollectionUtil.isNotEmpty(dailies)){
            dailies.forEach(d->{
                d.setLocation(location);
            });
            for (Daily d : dailies){
                dailyService.insert(d);
            }
        }




    }
}
