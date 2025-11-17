package com.app.websocket.controller;

import com.app.websocket.model.User;
import com.app.websocket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // GET /api/users  -> lấy tất cả user
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET /api/users/{id} -> lấy chi tiết 1 user
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"
                ));
    }

    // POST /api/users -> tạo user mới
    @PostMapping
    public User createUser(@RequestBody User user) {
        // set createdAt nếu chưa có
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(Instant.now());
        }
        // Đảm bảo id null để Mongo tự generate
        user.setId(null);
        return userRepository.save(user);
    }

    // PUT /api/users/{id} -> cập nhật user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User userRequest) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"
                ));

        existing.setName(userRequest.getName());
        existing.setUsername(userRequest.getUsername());
        existing.setPassword(userRequest.getPassword());
        existing.setEmail(userRequest.getEmail());
        // thường không đổi createdAt

        return userRepository.save(existing);
    }

    // DELETE /api/users/{id} -> xóa user
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found"
            );
        }
        userRepository.deleteById(id);
    }
}
