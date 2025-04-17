package com.atigu.weather.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class WeatherHourly {
    private int  id;
    private Date date;
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