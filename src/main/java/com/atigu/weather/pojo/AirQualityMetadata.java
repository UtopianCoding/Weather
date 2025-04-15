package com.atigu.weather.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("air_quality_metadata")
public class AirQualityMetadata {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String tag;
    private String createdAt;
}