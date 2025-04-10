package com.atigu.weather.service;

import com.atigu.weather.model.Daily;
import java.util.List;

public interface DailyService {
    Daily insert(Daily daily);
    Daily update(Daily daily);
    void deleteById(Integer id);
    Daily selectById(Integer id);
    List<Daily> selectAll();
    Daily selectByFxDate(String fxDate);
}