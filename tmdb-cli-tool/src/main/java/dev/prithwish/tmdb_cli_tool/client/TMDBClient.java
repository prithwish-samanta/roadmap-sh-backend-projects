package dev.prithwish.tmdb_cli_tool.client;

import dev.prithwish.tmdb_cli_tool.domain.Movie;
import dev.prithwish.tmdb_cli_tool.domain.TMDBResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class TMDBClient {
    @Value("${tmdb.api.base.url}")
    private String baseUrl;

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public TMDBClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Movie> nowPlaying() {
        HttpHeaders headers = getHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<TMDBResponse> response = restTemplate.exchange(
                baseUrl + "/now_playing?api_key=" + apiKey + "&language=en-US&page=1",
                HttpMethod.GET, entity, TMDBResponse.class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getResults();
        } else {
            throw new RuntimeException(Objects.requireNonNull(response.getBody()).getStatus_message());
        }
    }

    public List<Movie> popular() {
        HttpHeaders headers = getHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<TMDBResponse> response = restTemplate.exchange(
                baseUrl + "/popular?api_key=" + apiKey + "language=en-US&page=1",
                HttpMethod.GET, entity, TMDBResponse.class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getResults();
        } else {
            throw new RuntimeException(Objects.requireNonNull(response.getBody()).getStatus_message());
        }
    }

    public List<Movie> topRated() {
        HttpHeaders headers = getHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<TMDBResponse> response = restTemplate.exchange(
                baseUrl + "/top_rated?api_key=" + apiKey + "language=en-US&page=1",
                HttpMethod.GET, entity, TMDBResponse.class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getResults();
        } else {
            throw new RuntimeException(Objects.requireNonNull(response.getBody()).getStatus_message());
        }
    }

    public List<Movie> upcoming() {
        HttpHeaders headers = getHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<TMDBResponse> response = restTemplate.exchange(
                baseUrl + "/upcoming?api_key=" + apiKey + "language=en-US&page=1",
                HttpMethod.GET, entity, TMDBResponse.class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getResults();
        } else {
            throw new RuntimeException(Objects.requireNonNull(response.getBody()).getStatus_message());
        }
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/json");
        return headers;
    }
}
