package com.atigu.weather.mapper;


import com.atigu.weather.pojo.WeatherHourly;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface WeatherHourlyMapper {
    @Insert("INSERT INTO weather_hourly( time, temp, icon, text, precip, wind360, wind_dir, wind_scale, " +
            "wind_speed, humidity, pressure) VALUES( #{time}, #{temp}, #{icon}, #{text}, #{precip}, " +
            "#{wind360}, #{windDir}, #{windScale}, #{windSpeed}, #{humidity}, #{pressure})")
    int insert(WeatherHourly weatherHourly);

    @Insert("<script>" +
            "INSERT INTO weather_hourly( time, temp, icon, text, precip, wind360, wind_dir, wind_scale, " +
            "wind_speed, humidity, pressure) VALUES " +
            "<foreach collection='list' item='item' separator=','>" +
            "( #{item.time}, #{item.temp}, #{item.icon}, #{item.text}, #{item.precip}, #{item.wind360}, " +
            "#{item.windDir}, #{item.windScale}, #{item.windSpeed}, #{item.humidity}, #{item.pressure})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<WeatherHourly> weatherHourlyList);



    @Select("SELECT * FROM weather_hourly WHERE time BETWEEN #{startTime} AND #{endTime} ORDER BY time")
    List<WeatherHourly> selectByTimeRange(@Param("startTime") Date startTime, @Param("endTime") Date endTime);


}