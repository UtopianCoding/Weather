package com.atigu.weather.service;

import com.atigu.weather.pojo.WeatherDaily;
import com.atigu.weather.pojo.WeatherHourly;

import java.util.Date;
import java.util.List;

public interface WeatherHistoryService {

    WeatherDaily getDailyWeather(String date);

    List<WeatherDaily> getDailyWeatherRange(String startDate, String endDate);



    List<WeatherHourly> getHourlyWeatherRange(Date startTime, Date endTime);

    void saveDailyWeather(WeatherDaily weatherDaily);


}
