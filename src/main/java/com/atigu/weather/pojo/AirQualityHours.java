package com.atigu.weather.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("air_quality_hours")
public class AirQualityHours {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long metadataId;
    private String forecastTime;

}