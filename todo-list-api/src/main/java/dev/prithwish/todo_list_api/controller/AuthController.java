package dev.prithwish.todo_list_api.controller;

import dev.prithwish.todo_list_api.dto.LoginRequest;
import dev.prithwish.todo_list_api.dto.LoginResponse;
import dev.prithwish.todo_list_api.dto.SignUpRequest;
import dev.prithwish.todo_list_api.model.User;
import dev.prithwish.todo_list_api.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationService AuthenticationService;

    public AuthController(AuthenticationService AuthenticationService) {
        this.AuthenticationService = AuthenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody SignUpRequest request) {
        User user = AuthenticationService.createUser(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = AuthenticationService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
