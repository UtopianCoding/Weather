package com.atigu.weather.model;

import com.atigu.weather.pojo.Daily;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {
    private String code;
    private String updateTime;
    private String fxLink;
    private List<Daily> daily;
    private Refer refer;
 

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
 
    public String getUpdateTime() { return updateTime; }
    public void setUpdateTime(String updateTime) { this.updateTime = updateTime; }
 
    public String getFxLink() { return fxLink; }
    public void setFxLink(String fxLink) { this.fxLink = fxLink; }
 
    public List<Daily> getDaily() { return daily; }
    public void setDaily(List<Daily> daily) { this.daily = daily; }
 
    public Refer getRefer() { return refer; }
    public void setRefer(Refer refer) { this.refer = refer; }
}