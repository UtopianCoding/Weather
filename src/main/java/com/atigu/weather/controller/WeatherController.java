package com.atigu.weather.controller;

import com.atigu.weather.common.UResult;
import com.atigu.weather.model.WeatherRequest;
import com.atigu.weather.pojo.Daily;
import com.atigu.weather.service.DailyService;
import com.atigu.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {



    @Autowired
    private WeatherService weatherService;

    @Autowired
    private DailyService dailyService;

    @PostMapping("/weather")
    public UResult getWeather(@RequestBody WeatherRequest req) throws Exception {
        weatherService.getWeather( req);
        List<Daily> dailyList=dailyService.selectByLocation(req.getLongitude()+","+req.getLatitude());
        return UResult.success(dailyList);
    }

}
