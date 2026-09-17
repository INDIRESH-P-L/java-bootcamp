package com.indiresh.demo.controller;

import com.indiresh.demo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    // In-memory list to store users for demonstration
    private final List<User> userList = new ArrayList<>();

    public UserController() {
        // Adding sample users
        userList.add(new User(1L, "John", "Doe", "john.doe@example.com"));
        userList.add(new User(2L, "Jane", "Smith", "jane.smith@example.com"));
    }

    // 1. GET all users: GET /users
    @GetMapping
    public List<User> getAllUsers() {
        return userList;
    }

    // 2. GET user by ID: GET /users/{id}
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // 3. CREATE a new user: POST /users
    @PostMapping
    public String createUser(@RequestBody User user) {
        userList.add(user);
        return "User " + user.getFirstName() + " " + user.getLastName() + " created successfully!";
    }

    // 4. UPDATE an existing user: PUT /users/{id}
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        for (int i = 0; i < userList.size(); i++) {
            User existing = userList.get(i);
            if (existing.getId().equals(id)) {
                existing.setFirstName(updatedUser.getFirstName());
                existing.setLastName(updatedUser.getLastName());
                existing.setEmail(updatedUser.getEmail());
                return "User with ID " + id + " updated successfully!";
            }
        }
        return "User with ID " + id + " not found!";
    }

    // 5. DELETE a user: DELETE /users/{id}
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        boolean removed = userList.removeIf(user -> user.getId().equals(id));
        if (removed) {
            return "User with ID " + id + " deleted successfully!";
        } else {
            return "User with ID " + id + " not found!";
        }
    }
}
