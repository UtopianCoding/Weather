package com.atigu.weather.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AirQualityResponse {
    private Metadata metadata;
    private List<Hour> hours;
    
    @Data
    public static class Metadata {
        private String tag;
    }

    @Data
    public static class Hour {
        private String forecastTime;
        private List<Index> indexes;
        private List<Pollutant> pollutants;

    }
    

    
    @Data
    public static class Index {
        private String code;
        private String name;
        private Double aqi;
        private String aqiDisplay;
        private String level;
        private String category;
        private Color color;
        private PrimaryPollutant primaryPollutant;
        private Health health;
    }
    
    @Data
    public static class Pollutant {
        private String code;
        private String name;
        private String fullName;
        private Concentration concentration;
        private List<SubIndex> subIndexes;
    }
    
    @Data
    public static class Color {
        private Integer red;
        private Integer green;
        private Integer blue;
        private Double alpha;
    }
    
    @Data
    public static class PrimaryPollutant {
        private String code;
        private String name;
        private String fullName;
    }
    
    @Data
    public static class Health {
        private String effect;
        private Advice advice;
        
        @Data
        public static class Advice {
            private String generalPopulation;
            private String sensitivePopulation;
        }
    }
    
    @Data
    public static class Concentration {
        private Double value;
        private String unit;
    }
    
    @Data
    public static class SubIndex {
        private String code;
        private Double aqi;
        private String aqiDisplay;
    }
}