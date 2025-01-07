package dev.prithwish.tmdb_cli_tool.domain;

import java.util.List;

public class TMDBResponse {
    private int page;
    private int total_pages;
    private int total_results;
    private List<Movie> results;
    private String status_message;

    public TMDBResponse() {
    }

    public TMDBResponse(int page, int total_pages, int total_results, List<Movie> results, String statusMessage) {
        this.page = page;
        this.total_pages = total_pages;
        this.total_results = total_results;
        this.results = results;
        status_message = statusMessage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }
}
