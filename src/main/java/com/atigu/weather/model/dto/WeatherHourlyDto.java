package com.atigu.weather.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class WeatherHourlyDto {


    private String time;
    private Integer temp;
    private String icon;
    private String text;
    private Double precip;
    private Integer wind360;
    private String windDir;
    private String windScale;
    private Integer windSpeed;
    private Integer humidity;
    private Integer pressure;

}
