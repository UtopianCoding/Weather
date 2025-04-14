package com.atigu.weather.mapper;
import org.apache.ibatis.annotations.Param;

import com.atigu.weather.pojo.Daily;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DailyMapper {
    
    @Insert("INSERT INTO daily(fxDate, sunrise, sunset, moonrise, moonset, moonPhase, moonPhaseIcon, " +
            "tempMax, tempMin, iconDay, textDay, iconNight, textNight, wind360Day, windDirDay, windScaleDay, " +
            "windSpeedDay, wind360Night, windDirNight, windScaleNight, windSpeedNight, precip, uv_index, " +
            "humidity, pressure, vis, cloud,location) " +
            "VALUES(#{fxDate}, #{sunrise}, #{sunset}, #{moonrise}, #{moonset}, #{moonPhase}, #{moonPhaseIcon}, " +
            "#{tempMax}, #{tempMin}, #{iconDay}, #{textDay}, #{iconNight}, #{textNight}, #{wind360Day}, " +
            "#{windDirDay}, #{windScaleDay}, #{windSpeedDay}, #{wind360Night}, #{windDirNight}, #{windScaleNight}, " +
            "#{windSpeedNight}, #{precip}, #{uvIndex}, #{humidity}, #{pressure}, #{vis}, #{cloud},#{location} )")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Daily daily);

    @Update("UPDATE daily SET fxDate=#{fxDate}, sunrise=#{sunrise}, sunset=#{sunset}, moonrise=#{moonrise}, " +
            "moonset=#{moonset}, moonPhase=#{moonPhase}, moonPhaseIcon=#{moonPhaseIcon}, tempMax=#{tempMax}, " +
            "tempMin=#{tempMin}, iconDay=#{iconDay}, textDay=#{textDay}, iconNight=#{iconNight}, " +
            "textNight=#{textNight}, wind360Day=#{wind360Day}, windDirDay=#{windDirDay}, windScaleDay=#{windScaleDay}, " +
            "windSpeedDay=#{windSpeedDay}, wind360Night=#{wind360Night}, windDirNight=#{windDirNight}, " +
            "windScaleNight=#{windScaleNight}, windSpeedNight=#{windSpeedNight}, precip=#{precip}, " +
            "uv_index=#{uvIndex}, humidity=#{humidity}, pressure=#{pressure}, vis=#{vis}, cloud=#{cloud} ,location=#{location}" +
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

    Daily selectByFxDateAndLocation(@Param("fxDate")String fxDate,@Param("location")String location);

    Daily selectByLocation(@Param("location") String location,@Param("fxDate") String fxDate);

    /**
     *
     * @param location
     * @param monthStart
     * @param monthEnd
     * @return
     */
    List<Daily> selectByLocationAndMonth(@Param("location") String location, @Param("monthStart") String monthStart, @Param("monthEnd") String monthEnd);
}