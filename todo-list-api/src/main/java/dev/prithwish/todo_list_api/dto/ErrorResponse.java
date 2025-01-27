package dev.prithwish.todo_list_api.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private String error;
    private int status;

    public ErrorResponse() {
    }

    public ErrorResponse(LocalDateTime timestamp, String error, int status) {
        this.timestamp = timestamp;
        this.error = error;
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
