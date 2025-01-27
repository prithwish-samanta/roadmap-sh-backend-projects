package dev.prithwish.todo_list_api.controller;

import dev.prithwish.todo_list_api.dto.ToDoListResponse;
import dev.prithwish.todo_list_api.model.ToDo;
import dev.prithwish.todo_list_api.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todos")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping
    public ResponseEntity<ToDo> createToDoItem(@RequestBody ToDo toDo) {
        ToDo res = toDoService.createToDoItem(toDo);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateToDoItem(@PathVariable UUID id, @RequestBody ToDo toDo) {
        ToDo res = toDoService.updateToDoItem(id, toDo);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDoItem(@PathVariable UUID id) {
        toDoService.deleteToDoItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<ToDoListResponse> getToDoItems(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        ToDoListResponse res = toDoService.getToDoItems(page, limit);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
