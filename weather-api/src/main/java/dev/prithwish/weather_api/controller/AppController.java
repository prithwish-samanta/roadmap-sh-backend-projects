package dev.prithwish.weather_api.controller;

import dev.prithwish.weather_api.client.VisualCrossingClient;
import dev.prithwish.weather_api.model.WeatherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AppController {
    private static final Logger log = LoggerFactory.getLogger(AppController.class);
    private final VisualCrossingClient client;

    public AppController(VisualCrossingClient client) {
        this.client = client;
    }

    @GetMapping("/weather/{city}")
    public ResponseEntity<?> getWeather(@PathVariable String city) {
        try {
            WeatherData weatherData = client.getWeatherData(city.toLowerCase());
            return ResponseEntity.ok(weatherData);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
