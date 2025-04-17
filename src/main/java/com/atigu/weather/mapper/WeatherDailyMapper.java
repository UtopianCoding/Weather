package com.atigu.weather.mapper;


import com.atigu.weather.pojo.WeatherDaily;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface WeatherDailyMapper {
    @Insert("INSERT INTO weather_daily(date, sunrise, sunset, moonrise, moonset, moon_phase, temp_max, temp_min, " +
            "humidity, precip, pressure) VALUES(#{date}, #{sunrise}, #{sunset}, #{moonrise}, #{moonset}, " +
            "#{moonPhase}, #{tempMax}, #{tempMin}, #{humidity}, #{precip}, #{pressure})")
    int insert(WeatherDaily weatherDaily);

    @Select("SELECT * FROM weather_daily WHERE date = #{date}")
    WeatherDaily selectByDate(String date);

    @Select("SELECT * FROM weather_daily WHERE date BETWEEN #{startDate} AND #{endDate}")
    List<WeatherDaily> selectByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Update("UPDATE weather_daily SET sunrise=#{sunrise}, sunset=#{sunset}, moonrise=#{moonrise}, moonset=#{moonset}, " +
            "moon_phase=#{moonPhase}, temp_max=#{tempMax}, temp_min=#{tempMin}, humidity=#{humidity}, " +
            "precip=#{precip}, pressure=#{pressure} WHERE date=#{date}")
    int update(WeatherDaily weatherDaily);

    @Delete("DELETE FROM weather_daily WHERE date = #{date}")
    int deleteByDate(String date);
}