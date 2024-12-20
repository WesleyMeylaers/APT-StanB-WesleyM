package fact.it.userservice.controller;

import fact.it.userservice.dto.UserResponse;
import fact.it.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // http://localhost:8081/api/users
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    // http://localhost:8081/api/users/{id}
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
