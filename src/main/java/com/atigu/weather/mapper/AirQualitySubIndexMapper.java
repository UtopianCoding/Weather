package com.atigu.weather.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.atigu.weather.pojo.AirQualitySubIndex;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AirQualitySubIndexMapper  {
    List<AirQualitySubIndex> findAll();


    void insert(AirQualitySubIndex entity);
}