package dev.prithwish.todo_list_api.dto;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, String error, int status) {
}
