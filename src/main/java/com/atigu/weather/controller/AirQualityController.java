package com.atigu.weather.controller;

import com.atigu.weather.common.UResult;
import com.atigu.weather.service.AirQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// controller/AirQualityController.java
@RestController
@RequestMapping("/air-quality")

public class AirQualityController {
    @Autowired
    private  AirQualityService airQualityService;
    
    @PostMapping("/fetch")
    public UResult<String> fetchData() throws Exception {
        airQualityService.fetchAndSaveAirQualityData();
        return UResult.success("Air quality data fetched and saved successfully");
    }
    

}