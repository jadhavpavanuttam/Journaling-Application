package com.example.journalingapp.cache;

import com.example.journalingapp.repository.ConfigJournalAppRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AppCache {
    public enum Keys {
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepo repo;
    private Map<String, String> appCache = new HashMap<>(10, 2.3f); //by default 16 & 0.75

    @PostConstruct
    public void init() {
        repo.findAll(); // all the
        appCache = null;
    }
}
/**
 * we have to make this all for external API's playing with those.
 * get access key -> value pairs.
 * into the database.
 */