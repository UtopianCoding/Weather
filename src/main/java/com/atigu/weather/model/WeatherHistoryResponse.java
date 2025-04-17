package com.atigu.weather.model;

import com.atigu.weather.model.dto.WeatherDailyDto;
import com.atigu.weather.model.dto.WeatherHourlyDto;
import com.atigu.weather.pojo.WeatherDaily;
import com.atigu.weather.pojo.WeatherHourly;
import lombok.Data;

import java.util.List;

@Data
public class WeatherHistoryResponse {
    private WeatherDailyDto weatherDaily;
    private List<WeatherHourlyDto> weatherHourly;
}
