package dev.prithwish.todo_list_api.service;

import dev.prithwish.todo_list_api.dto.ToDoListResponse;
import dev.prithwish.todo_list_api.exception.ItemNotFoundException;
import dev.prithwish.todo_list_api.model.ToDo;
import dev.prithwish.todo_list_api.repository.ToDoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class ToDoServiceImpl implements ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public ToDo createToDoItem(ToDo item) {
        return toDoRepository.save(item);
    }

    @Override
    public ToDo updateToDoItem(UUID id, ToDo item) {
        ToDo todo = toDoRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("No todo found with id: " + id));
        if (StringUtils.hasText(item.getTitle())) {
            todo.setTitle(item.getTitle());
        }
        if (StringUtils.hasText(item.getDescription())) {
            todo.setDescription(item.getDescription());
        }
        return toDoRepository.save(todo);
    }

    @Override
    public void deleteToDoItem(UUID id) {
        ToDo todo = toDoRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("No todo found with id: " + id));
        toDoRepository.delete(todo);
    }

    @Override
    public ToDoListResponse getToDoItems(int page, int limit) {
        ToDoListResponse response = new ToDoListResponse();
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<ToDo> pageDetails = toDoRepository.findAll(pageable);
        response.setData(pageDetails.getContent());
        response.setPage(pageDetails.getNumber() + 1);
        response.setLimit(pageDetails.getSize());
        response.setTotal(pageDetails.getTotalElements());
        return response;
    }
}
