package com.atigu.weather.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class WeatherDaily {
    private int id;
    private String date;
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private String moonPhase;
    private Integer tempMax;
    private Integer tempMin;
    private Integer humidity;
    private Double precip;
    private Integer pressure;

}