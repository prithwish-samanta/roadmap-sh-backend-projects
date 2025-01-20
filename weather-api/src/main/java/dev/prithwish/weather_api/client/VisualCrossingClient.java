package dev.prithwish.weather_api.client;

import dev.prithwish.weather_api.model.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VisualCrossingClient {
    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public VisualCrossingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "weather",key = "#city")
    public WeatherData getWeatherData(String city) {
        String url = apiUrl + city + "/today?key=" + apiKey;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/json");
        return restTemplate.getForObject(url, WeatherData.class, headers);
    }
}
