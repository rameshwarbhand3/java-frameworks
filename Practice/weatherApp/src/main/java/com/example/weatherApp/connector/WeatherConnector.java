package com.example.weatherApp.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WeatherConnector {

    private final String appid;
    private final String weatherEndPoint;

    @Autowired
    public WeatherConnector(String appid, String weatherEndPoint) {
        this.appid = appid;
        this.weatherEndPoint = weatherEndPoint;
    }

    private final RestTemplate resttemplate = new RestTemplate();

    public String getWeatherDetails(String city) {
        String weatherDetails;

        String url = UriComponentsBuilder
                .fromHttpUrl(weatherEndPoint)
                .queryParam("q", city)
                .queryParam("appid", appid)
                .build()
                .toString();

        weatherDetails = resttemplate.getForObject(url, String.class);

        return weatherDetails;
    }
}
