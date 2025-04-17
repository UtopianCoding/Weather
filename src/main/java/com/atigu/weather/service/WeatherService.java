package com.atigu.weather.service;

import com.atigu.weather.model.WeatherRequest;

public interface WeatherService {
    /**
     *
     * @param request
     * @throws Exception
     */
    void getWeather(WeatherRequest request) throws Exception;

    void getHistoricalWeather() throws Exception;
}