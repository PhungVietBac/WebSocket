package com.app.websocket.repository;

import com.app.websocket.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    // Có thể dùng sau nếu cần login
    User findByUsername(String username);
}
