package com.atigu.weather.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("air_quality_pollutants")
public class AirQualityPollutant {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long dayId;
    private String code;
    private String name;
    private String fullName;
    private Double concentrationValue;
    private String concentrationUnit;
}