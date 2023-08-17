package com.example.weatherApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class WeatherConfig {

    @Value("${appid}")
    private String appid;

    @Value("${weather_endpoint}")
    private String weatherEndPoint;

    @Value("${weather_map}")
    private String weatherMap;

    @Bean
    public String weatherEndPoint() {
        return this.weatherEndPoint;
    }

    @Bean
    public String appid() {
        return this.appid;
    }

    @Bean
    public String weatherMap() {
        return this.weatherMap;
    }

}

