package dev.prithwish.todo_list_api.service;

import dev.prithwish.todo_list_api.dto.LoginRequest;
import dev.prithwish.todo_list_api.dto.LoginResponse;
import dev.prithwish.todo_list_api.dto.SignUpRequest;
import dev.prithwish.todo_list_api.exception.EmailAlreadyExistsException;
import dev.prithwish.todo_list_api.model.User;
import dev.prithwish.todo_list_api.repository.UserRepository;
import dev.prithwish.todo_list_api.util.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthenticationServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public User createUser(SignUpRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = new User();
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setName(request.name());
        return userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        String jwtToken = jwtUtils.generateToken(new org.springframework.security.core.userdetails.User(request.email(), request.password(), null));
        return new LoginResponse(jwtToken);
    }
}
