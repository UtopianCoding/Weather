package com.atigu.weather.mapper;

import com.atigu.weather.model.Daily;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DailyMapper {
    
    @Insert("INSERT INTO daily(fxDate, sunrise, sunset, moonrise, moonset, moonPhase, moonPhaseIcon, " +
            "tempMax, tempMin, iconDay, textDay, iconNight, textNight, wind360Day, windDirDay, windScaleDay, " +
            "windSpeedDay, wind360Night, windDirNight, windScaleNight, windSpeedNight, precip, uv_index, " +
            "humidity, pressure, vis, cloud) " +
            "VALUES(#{fxDate}, #{sunrise}, #{sunset}, #{moonrise}, #{moonset}, #{moonPhase}, #{moonPhaseIcon}, " +
            "#{tempMax}, #{tempMin}, #{iconDay}, #{textDay}, #{iconNight}, #{textNight}, #{wind360Day}, " +
            "#{windDirDay}, #{windScaleDay}, #{windSpeedDay}, #{wind360Night}, #{windDirNight}, #{windScaleNight}, " +
            "#{windSpeedNight}, #{precip}, #{uvIndex}, #{humidity}, #{pressure}, #{vis}, #{cloud})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Daily daily);

    @Update("UPDATE daily SET fxDate=#{fxDate}, sunrise=#{sunrise}, sunset=#{sunset}, moonrise=#{moonrise}, " +
            "moonset=#{moonset}, moonPhase=#{moonPhase}, moonPhaseIcon=#{moonPhaseIcon}, tempMax=#{tempMax}, " +
            "tempMin=#{tempMin}, iconDay=#{iconDay}, textDay=#{textDay}, iconNight=#{iconNight}, " +
            "textNight=#{textNight}, wind360Day=#{wind360Day}, windDirDay=#{windDirDay}, windScaleDay=#{windScaleDay}, " +
            "windSpeedDay=#{windSpeedDay}, wind360Night=#{wind360Night}, windDirNight=#{windDirNight}, " +
            "windScaleNight=#{windScaleNight}, windSpeedNight=#{windSpeedNight}, precip=#{precip}, " +
            "uv_index=#{uvIndex}, humidity=#{humidity}, pressure=#{pressure}, vis=#{vis}, cloud=#{cloud} " +
            "WHERE id=#{id}")
    int update(Daily daily);

    @Delete("DELETE FROM daily WHERE id=#{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM daily WHERE id=#{id}")
    Daily selectById(Integer id);

    @Select("SELECT * FROM daily ORDER BY fxDate DESC")
    List<Daily> selectAll();

    @Select("SELECT * FROM daily WHERE fxDate=#{fxDate}")
    Daily selectByFxDate(String fxDate);
}