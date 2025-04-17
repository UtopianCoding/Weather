package com.atigu.weather.service;

import com.atigu.weather.model.AirQualityResponse;
import com.atigu.weather.model.WeatherHistoryResponse;
import com.atigu.weather.pojo.Daily;

import java.util.List;

/**
 * @BelongsProject: weather
 * @BelongsPackage: com.atigu.weather.service
 * @ClassName ThirdService
 * @Author: Utopia
 * @Description: TODO
 * @Version: 1.0
 */
public interface ThirdService {

    public List<Daily> getDayWeather(String location) throws Exception;

    AirQualityResponse fetchAndSaveAirQualityData() throws Exception;

    WeatherHistoryResponse getHistoricalWeather() throws Exception;
}
