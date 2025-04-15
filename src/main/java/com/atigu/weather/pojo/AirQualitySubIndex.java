package com.atigu.weather.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("air_quality_sub_indexes")
public class AirQualitySubIndex {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long pollutantId;
    private String code;
    private Double aqi;
    private String aqiDisplay;
}