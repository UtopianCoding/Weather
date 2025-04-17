package com.atigu.weather.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.atigu.weather.common.UResult;
import com.atigu.weather.model.WeatherRequest;
import com.atigu.weather.pojo.Daily;
import com.atigu.weather.service.DailyService;
import com.atigu.weather.service.WeatherService;
import com.atigu.weather.utils.DateUtils;
import com.atigu.weather.utils.cache.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
public class WeatherController {



    @Autowired
    private WeatherService weatherService;

    @Autowired
    private DailyService dailyService;

    @Resource
    private RedisCache redisUtil;

    @PostMapping("/weather")
    public UResult getWeather(@RequestBody WeatherRequest req) throws Exception {

        String fxDate = DateUtils.getNowDate();

        Daily daily=dailyService.selectByLocation(req.getLongitude()+","+req.getLatitude(),fxDate);
        if (daily==null){
            weatherService.getWeather( req);
            daily=dailyService.selectByLocation(req.getLongitude()+","+req.getLatitude(),fxDate);
        }
        return UResult.success(daily);
    }

    @PostMapping("/weather/month")
    public UResult getWeatherAllMonth(@RequestBody WeatherRequest req) throws Exception {

        Date monthDateStart = DateUtils.getMonthDateStart(new Date());
        Date monthDateEnd = DateUtils.getMonthDateEnd(new Date());

        String monthStart = DateUtils.formatDate(monthDateStart);
        String monthEnd = DateUtils.formatDate(monthDateEnd);
        String location = req.getLongitude() + "," + req.getLatitude();
        List<Daily> dailys=dailyService.selectByLocationAndMonth(location,monthStart,monthEnd);
        String redisCode = (String) redisUtil.get(location+monthStart+monthEnd);
        if (StrUtil.isNotBlank(redisCode)){
            List<Daily> list = JSONUtil.toList(redisCode, Daily.class);
            return UResult.success(list);
        }else{
            redisUtil.set(location+monthStart+monthEnd, JSONUtil.toJsonStr(dailys),30);
        }

        return UResult.success(dailys);
    }

    @PostMapping("/getHistory")
    public UResult<String> getHistory() throws Exception {
        weatherService.getHistoricalWeather();
        return UResult.success("Air quality data fetched and saved successfully");
    }

}
