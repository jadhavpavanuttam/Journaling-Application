package com.example.journalingapp.awsConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApiConfig {

    @Bean
    public RestClient weatherRestClient() {
        return RestClient.builder()
                .baseUrl("https://openweathermap.org")
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
