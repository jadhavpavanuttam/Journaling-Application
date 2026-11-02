package com.example.journalingapp.service;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class WeatherResponse {

    @Getter
    @Setter
    // Getters and Setters
    private String name; // City name
    private Main main;
    private List<Weather> weather;
    private Wind wind;

    // Nested Class for "main": { "temp": 298.48, "feels_like": 300.1, ... }
    @Setter
    @Getter
    public static class Main {
        private double temp;

        @JsonProperty("feels_like") // Maps JSON snake_case to Java camelCase
        private double feelsLike;
        private int humidity;

    }

    // Nested Class for "weather": [ { "main": "Clouds", "description": "scattered clouds" } ]
    @Setter
    @Getter
    public static class Weather {
        private String main;
        private String description;

    }

    // Nested Class for "wind": { "speed": 4.12 }
    @Setter
    @Getter
    public static class Wind {
        private double speed;

    }
}
