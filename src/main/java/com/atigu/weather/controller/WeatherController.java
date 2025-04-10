package com.atigu.weather.controller;

import com.atigu.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {



    @Autowired
    private WeatherService weatherService;

    @PostMapping("/weather")
    public void getWeather() throws Exception {
        weatherService.getWeather();
    }

}
