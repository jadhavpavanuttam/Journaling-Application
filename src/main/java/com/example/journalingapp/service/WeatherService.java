package com.example.journalingapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

//@Service
public class WeatherService {

    private final RestClient restClient;

    public WeatherService(RestClient restClient) {
        this.restClient = restClient;
    }

    public com.example.journalingapp.service.WeatherResponse fetchCurrentWeather(String cityName, String apiKey) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/weather")
                        .queryParam("q", cityName)
                        .queryParam("appid", apiKey)
                        .build())
                .retrieve()
                .body(com.example.journalingapp.service.WeatherResponse.class); // Automatically maps JSON to your POJO
    }
}
