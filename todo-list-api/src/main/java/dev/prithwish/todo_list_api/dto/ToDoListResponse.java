package dev.prithwish.todo_list_api.dto;

import dev.prithwish.todo_list_api.model.ToDo;

import java.util.List;

public class ToDoListResponse {
    private List<ToDo> data;
    private int page;
    private int limit;
    private long total;

    public ToDoListResponse() {
    }

    public ToDoListResponse(List<ToDo> data, int page, int limit, long total) {
        this.data = data;
        this.page = page;
        this.limit = limit;
        this.total = total;
    }

    public List<ToDo> getData() {
        return data;
    }

    public void setData(List<ToDo> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
