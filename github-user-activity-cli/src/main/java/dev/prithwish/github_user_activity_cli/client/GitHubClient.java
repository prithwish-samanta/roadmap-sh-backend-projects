package dev.prithwish.github_user_activity_cli.client;

import dev.prithwish.github_user_activity_cli.domain.Activity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GitHubClient {
    @Value("${github.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public GitHubClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Activity> getUserActivities(String userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Activity>> response = restTemplate.exchange(
                apiUrl + userId + "/events",
                HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }
}
