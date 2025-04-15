package com.atigu.weather.mapper;
import org.apache.ibatis.annotations.Param;

import com.atigu.weather.pojo.AirQualityMetadata;


public interface AirQualityMetadataMapper  {
    AirQualityMetadata findAllById(@Param("id")Long id);


    void insert(AirQualityMetadata metadata);
}