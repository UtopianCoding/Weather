package com.atigu.weather.service.impl;

import com.atigu.weather.mapper.*;
import com.atigu.weather.model.AirQualityResponse;
import com.atigu.weather.pojo.*;
import com.atigu.weather.service.AirQualityService;
import com.atigu.weather.service.ThirdService;
import com.atigu.weather.utils.DateUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class AirQualityServiceImpl implements AirQualityService {


    @Autowired
    private  AirQualityMetadataMapper metadataMapper;
    @Autowired
    private  AirQualityHoursMapper dayMapper;
    @Autowired
    private  AirQualityIndexMapper indexMapper;
    @Autowired
    private  AirQualityPollutantMapper pollutantMapper;
    @Autowired
    private  AirQualitySubIndexMapper subIndexMapper;
    


    @Autowired
    private ThirdService thirdService;

    @Override
    @Transactional
    public void fetchAndSaveAirQualityData() throws Exception {
        // 调用第三方API
        AirQualityResponse response = thirdService.fetchAndSaveAirQualityData();


        if (response != null && response.getHours() != null) {
            // 保存元数据
            AirQualityMetadata metadata = new AirQualityMetadata();
            metadata.setTag(response.getMetadata().getTag());
            metadata.setCreatedAt(DateUtils.formatDate(new Date()));
            metadataMapper.insert(metadata);
            
            // 保存每天数据
            for (AirQualityResponse.Hour day : response.getHours()) {
                saveDayData(metadata.getId(), day);
            }
        }
    }
    
    private void saveDayData(Long metadataId, AirQualityResponse.Hour dayData) {
        // 保存Day
        AirQualityHours day = new AirQualityHours();
        day.setMetadataId(metadataId);
        day.setForecastTime(dayData.getForecastTime());

        dayMapper.insert(day);
        
        // 保存Indexes
        if (dayData.getIndexes() != null) {
            for (AirQualityResponse.Index index : dayData.getIndexes()) {
                saveIndexData(day.getId(), index);
            }
        }
        
        // 保存Pollutants
        if (dayData.getPollutants() != null) {
            for (AirQualityResponse.Pollutant pollutant : dayData.getPollutants()) {
                savePollutantData(day.getId(), pollutant);
            }
        }
    }
    
    private void saveIndexData(Long dayId, AirQualityResponse.Index index) {
        AirQualityIndex entity = new AirQualityIndex();
        entity.setDayId(dayId);
        entity.setCode(index.getCode());
        entity.setName(index.getName());
        entity.setAqi(index.getAqi());
        entity.setAqiDisplay(index.getAqiDisplay());
        entity.setLevel(index.getLevel());
        entity.setCategory(index.getCategory());
        
        if (index.getColor() != null) {
            entity.setColorRed(index.getColor().getRed());
            entity.setColorGreen(index.getColor().getGreen());
            entity.setColorBlue(index.getColor().getBlue());
            entity.setColorAlpha(index.getColor().getAlpha());
        }
        
        if (index.getPrimaryPollutant() != null) {
            entity.setPollutantCode(index.getPrimaryPollutant().getCode());
            entity.setPollutantName(index.getPrimaryPollutant().getName());
            entity.setPollutantFullName(index.getPrimaryPollutant().getFullName());
        }
        
        if (index.getHealth() != null) {
            entity.setHealthEffect(index.getHealth().getEffect());
            if (index.getHealth().getAdvice() != null) {
                entity.setGeneralPopulationAdvice(index.getHealth().getAdvice().getGeneralPopulation());
                entity.setSensitivePopulationAdvice(index.getHealth().getAdvice().getSensitivePopulation());
            }
        }
        
        indexMapper.insert(entity);
    }
    
    private void savePollutantData(Long dayId, AirQualityResponse.Pollutant pollutant) {
        AirQualityPollutant entity = new AirQualityPollutant();
        entity.setDayId(dayId);
        entity.setCode(pollutant.getCode());
        entity.setName(pollutant.getName());
        entity.setFullName(pollutant.getFullName());
        
        if (pollutant.getConcentration() != null) {
            entity.setConcentrationValue(pollutant.getConcentration().getValue());
            entity.setConcentrationUnit(pollutant.getConcentration().getUnit());
        }
        
        pollutantMapper.insert(entity);
        
        // 保存SubIndexes
        if (pollutant.getSubIndexes() != null) {
            for (AirQualityResponse.SubIndex subIndex : pollutant.getSubIndexes()) {
                saveSubIndexData(entity.getId(), subIndex);
            }
        }
    }
    
    private void saveSubIndexData(Long pollutantId, AirQualityResponse.SubIndex subIndex) {
        AirQualitySubIndex entity = new AirQualitySubIndex();
        entity.setPollutantId(pollutantId);
        entity.setCode(subIndex.getCode());
        entity.setAqi(subIndex.getAqi());
        entity.setAqiDisplay(subIndex.getAqiDisplay());
        subIndexMapper.insert(entity);
    }
    

}