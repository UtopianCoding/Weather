package com.atigu.weather.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;


import com.atigu.weather.pojo.AirQualityHours;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AirQualityHoursMapper  {
    List<AirQualityHours> findAll();


    void insert(AirQualityHours day);
}