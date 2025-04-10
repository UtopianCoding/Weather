package com.atigu.weather.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
public class Daily {

    @Column(name = "id")
    private Integer id;

    @ApiModelProperty(value = "预报日期")
    @Column(name = "fxDate")
    private String fxDate;
    @ApiModelProperty(value = "日出时间")
    @Column(name = "sunrise")
    private String sunrise;
    @ApiModelProperty(value = "日落时间")
    @Column(name = "sunset")
    private String sunset;
    @ApiModelProperty(value = "月出时间")
    @Column(name = "moonrise")
    private String moonrise;
    @ApiModelProperty(value = "月落时间")
    @Column(name = "moonset")
    private String moonset;
    @ApiModelProperty(value = "月相")
    @Column(name = "moonPhase")
    private String moonPhase;
    @ApiModelProperty(value = "月相图标")
    @Column(name = "moonPhaseIcon")
    private String moonPhaseIcon;

    @ApiModelProperty(value = "最高温度")
    @Column(name = "tempMax")
    private String tempMax;
    @ApiModelProperty(value = "最低温度")
    @Column(name = "tempMin")
    private String tempMin;
    @ApiModelProperty(value = "白天天气图标")
    @Column(name = "iconDay")
    private String iconDay;
    @ApiModelProperty(value = "白天天气现象文字描述")
    @Column(name = "textDay")
    private String textDay;
    @ApiModelProperty(value = "晚上天气图标")
    @Column(name = "iconNight")
    private String iconNight;
    @ApiModelProperty(value = "晚上天气现象文字描述")
    @Column(name = "textNight")
    private String textNight;

    @ApiModelProperty(value = "预报白天风向360角度")
    @Column(name = "wind360Day")
    private String wind360Day ;
    @ApiModelProperty(value = "预报白天风向")
    @Column(name = "windDirDay")
    private String windDirDay ;
    @ApiModelProperty(value = "预报白天风力等级")
    @Column(name = "windScaleDay")
    private String windScaleDay ;
    @ApiModelProperty(value = "预报白天风速，公里/小时")
    @Column(name = "windSpeedDay")
    private String windSpeedDay ;
    @ApiModelProperty(value = "预报夜间风向360角度")
    @Column(name = "wind360Night")
    private String wind360Night ;
    @ApiModelProperty(value = "预报夜间风向")
    @Column(name = "windDirNight")
    private String windDirNight ;
    @ApiModelProperty(value = "预报夜间风力等级")
    @Column(name = "windScaleNight")
    private String windScaleNight ;
    @ApiModelProperty(value = "预报夜间风速，公里/小时")
    @Column(name = "windSpeedNight")
    private String windSpeedNight ;
    @ApiModelProperty(value = "预报当天总降水量，默认单位：毫米")
    @Column(name = "precip")
    private String precip ;
    @ApiModelProperty(value = "紫外线强度指数")
    @Column(name = "uv_index")
    private String uvIndex ;
    @ApiModelProperty(value = "相对湿度，百分比数值")
    @Column(name = "humidity")
    private String humidity ;
    @ApiModelProperty(value = "气压，默认单位：百帕")
    @Column(name = "pressure")
    private String pressure ;
    @ApiModelProperty(value = "能见度，默认单位：公里")
    @Column(name = "vis")
    private String vis ;
    @ApiModelProperty(value = "云量，百分比数值。可能为空")
    @Column(name = "cloud")
    private String cloud ;
}