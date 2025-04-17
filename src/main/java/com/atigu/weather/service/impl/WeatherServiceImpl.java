package com.atigu.weather.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.atigu.weather.mapper.WeatherDailyMapper;
import com.atigu.weather.mapper.WeatherHourlyMapper;
import com.atigu.weather.model.WeatherHistoryResponse;
import com.atigu.weather.model.WeatherRequest;
import com.atigu.weather.model.dto.WeatherDailyDto;
import com.atigu.weather.model.dto.WeatherHourlyDto;
import com.atigu.weather.pojo.Daily;
import com.atigu.weather.model.WeatherResponse;
import com.atigu.weather.pojo.WeatherDaily;
import com.atigu.weather.pojo.WeatherHourly;
import com.atigu.weather.service.DailyService;
import com.atigu.weather.service.ThirdService;
import com.atigu.weather.service.WeatherService;

import com.atigu.weather.utils.DateUtils;
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
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private WeatherDailyMapper weatherDailyMapper;

    @Autowired
    private WeatherHourlyMapper weatherHourlyMapper;

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

    @Override
    public void getHistoricalWeather() throws Exception {
        WeatherHistoryResponse historicalWeather = thirdService.getHistoricalWeather();
        if (historicalWeather!=null){
            WeatherDailyDto weatherDailydto = historicalWeather.getWeatherDaily();
            WeatherDaily weatherDaily=new WeatherDaily();
            if (weatherDailydto!=null){
                BeanUtil.copyProperties(weatherDailydto,weatherDaily);

                weatherDailyMapper.insert(weatherDaily);
            }
            List<WeatherHourlyDto> weatherDailyList = historicalWeather.getWeatherHourly();
            if (CollectionUtil.isNotEmpty(weatherDailyList)){
                weatherDailyList.stream().forEach(v->{
                        WeatherHourly weatherHourly=     new WeatherHourly();

                    // 使用 OffsetDateTime 解析字符串
                    OffsetDateTime offsetDateTime = OffsetDateTime.parse(v.getTime());

                    // 输出解析后的日期时间
                    System.out.println("Parsed OffsetDateTime: " + offsetDateTime);

                    String formattedDateTime = offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                    v.setTime(formattedDateTime);
                        BeanUtil.copyProperties(v,weatherHourly);

                        if (weatherHourly!=null){
                            weatherHourlyMapper.insert(weatherHourly);
                        }
                    }

                );
            }
        }
    }
}
