package com.atigu.weather.model;

import lombok.Data;

/**
 * @BelongsProject: weather
 * @BelongsPackage: com.atigu.weather.model
 * @ClassName weatherRequest
 * @Author: Utopia
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class WeatherRequest {

    private String latitude;
    private String longitude;

    private String type;
}
