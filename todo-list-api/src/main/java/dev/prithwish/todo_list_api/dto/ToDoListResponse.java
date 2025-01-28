package dev.prithwish.todo_list_api.dto;

import dev.prithwish.todo_list_api.model.ToDo;

import java.util.List;

public record ToDoListResponse(List<ToDo> data, int page, int limit, long total) {
}
