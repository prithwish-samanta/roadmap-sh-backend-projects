package dev.prithwish.todo_list_api.service;

import dev.prithwish.todo_list_api.dto.LoginRequest;
import dev.prithwish.todo_list_api.dto.LoginResponse;
import dev.prithwish.todo_list_api.dto.SignUpRequest;
import dev.prithwish.todo_list_api.model.User;

public interface AuthenticationService {
    User createUser(SignUpRequest request);

    LoginResponse login(LoginRequest request);
}
