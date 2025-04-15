package com.atigu.weather.service;



import com.atigu.weather.pojo.AirQualityHours;

import java.util.List;

public interface AirQualityService {
    void fetchAndSaveAirQualityData() throws Exception;
}