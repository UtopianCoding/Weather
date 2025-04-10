package com.atigu.weather.controller;

import com.atigu.weather.model.Daily;
import com.atigu.weather.service.DailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "每日天气数据管理")
@RestController
@RequestMapping("/api/daily")
public class DailyController {

    @Autowired
    private DailyService dailyService;

    @ApiOperation("新增每日天气数据")
    @PostMapping
    public Daily insert(@RequestBody Daily daily) {
        return dailyService.insert(daily);
    }

    @ApiOperation("修改每日天气数据")
    @PutMapping
    public Daily update(@RequestBody Daily daily) {
        return dailyService.update(daily);
    }

    @ApiOperation("删除每日天气数据")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        dailyService.deleteById(id);
    }

    @ApiOperation("根据ID查询每日天气数据")
    @GetMapping("/{id}")
    public Daily selectById(@PathVariable Integer id) {
        return dailyService.selectById(id);
    }

    @ApiOperation("查询所有每日天气数据")
    @GetMapping
    public List<Daily> selectAll() {
        return dailyService.selectAll();
    }

    @ApiOperation("根据预报日期查询天气数据")
    @GetMapping("/date/{fxDate}")
    public Daily selectByFxDate(@PathVariable String fxDate) {
        return dailyService.selectByFxDate(fxDate);
    }
}