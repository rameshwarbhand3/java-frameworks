package com.example.weatherApp.service;

import com.example.weatherApp.connector.WeatherConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private WeatherConnector weatherConnector;

    public String getWeatherDetails(String city) {
        return weatherConnector.getWeatherDetails(city);
    }
}

