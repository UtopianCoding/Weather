package com.atigu.weather.service.impl;

import com.atigu.weather.mapper.DailyMapper;
import com.atigu.weather.pojo.Daily;
import com.atigu.weather.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DailyServiceImpl implements DailyService {

    @Autowired
    private DailyMapper dailyMapper;

    @Override
    public Daily insert(Daily daily) {
        Daily fxDate = dailyMapper.selectByFxDateAndLocation(daily.getFxDate(),daily.getLocation());
        if (fxDate != null){
            dailyMapper.update(daily);
        }else{
            dailyMapper.insert(daily);
        }

        return daily;
    }

    @Override
    public Daily update(Daily daily) {
        dailyMapper.update(daily);
        return daily;
    }

    @Override
    public void deleteById(Integer id) {
        dailyMapper.deleteById(id);
    }

    @Override
    public Daily selectById(Integer id) {
        return dailyMapper.selectById(id);
    }

    @Override
    public List<Daily> selectAll() {
        return dailyMapper.selectAll();
    }

    @Override
    public Daily selectByFxDate(String fxDate) {
        return dailyMapper.selectByFxDate(fxDate);
    }

    @Override
    public Daily selectByLocation(String location,String fxDate) {
        return dailyMapper.selectByLocation(location,fxDate);
    }

    @Override
    public List<Daily> selectByLocationAndMonth(String location, String monthStart, String monthEnd) {
        return dailyMapper.selectByLocationAndMonth(location,monthStart,monthEnd);
    }
}