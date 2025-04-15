package com.atigu.weather.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("air_quality_indexes")
public class AirQualityIndex {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long dayId;
    private String code;
    private String name;
    private Double aqi;
    private String aqiDisplay;
    private String level;
    private String category;
    private Integer colorRed;
    private Integer colorGreen;
    private Integer colorBlue;
    private Double colorAlpha;
    private String pollutantCode;
    private String pollutantName;
    private String pollutantFullName;
    private String healthEffect;
    private String generalPopulationAdvice;
    private String sensitivePopulationAdvice;
}