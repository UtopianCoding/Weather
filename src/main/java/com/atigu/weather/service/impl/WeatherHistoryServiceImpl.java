package com.atigu.weather.service.impl;

import com.atigu.weather.mapper.WeatherDailyMapper;
import com.atigu.weather.mapper.WeatherHourlyMapper;
import com.atigu.weather.pojo.WeatherDaily;
import com.atigu.weather.pojo.WeatherHourly;
import com.atigu.weather.service.WeatherHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class WeatherHistoryServiceImpl implements WeatherHistoryService {

    @Autowired
    private WeatherDailyMapper weatherDailyMapper;

    @Autowired
    private WeatherHourlyMapper weatherHourlyMapper;

    @Override
    public WeatherDaily getDailyWeather(String date) {
        return weatherDailyMapper.selectByDate(date);
    }

    @Override
    public List<WeatherDaily> getDailyWeatherRange(String startDate, String endDate) {
        return weatherDailyMapper.selectByDateRange(startDate, endDate);
    }



    @Override
    public List<WeatherHourly> getHourlyWeatherRange(Date startTime, Date endTime) {
        return weatherHourlyMapper.selectByTimeRange(startTime, endTime);
    }

    @Override
    @Transactional
    public void saveDailyWeather(WeatherDaily weatherDaily) {
        WeatherDaily existing = weatherDailyMapper.selectByDate(weatherDaily.getDate());
        if (existing != null) {
            weatherDailyMapper.update(weatherDaily);
        } else {
            weatherDailyMapper.insert(weatherDaily);
        }
    }




}
