package dev.prithwish.todo_list_api.service;

import dev.prithwish.todo_list_api.dto.ToDoListResponse;
import dev.prithwish.todo_list_api.model.ToDo;

import java.util.UUID;

public interface ToDoService {
    ToDo createToDoItem(ToDo item);

    ToDo updateToDoItem(UUID id, ToDo item);

    void deleteToDoItem(UUID id);

    ToDoListResponse getToDoItems(int page, int limit);
}
