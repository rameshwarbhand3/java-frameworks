package com.example.weatherApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.weatherApp.service.WeatherService;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    public String fetchWeatherDetails(@RequestParam(value = "city") String city) {

        try {
            return weatherService.getWeatherDetails(city);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}


