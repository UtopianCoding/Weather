package com.atigu.weather.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @BelongsProject: weather
 * @BelongsPackage: com.atigu.weather.utils
 * @ClassName configurer
 * @Author: Utopia
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class configurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // 允许的前端域名
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的 HTTP 方法
                        .allowedHeaders("*"); // 允许的请求头
            }
        };
    }

}
