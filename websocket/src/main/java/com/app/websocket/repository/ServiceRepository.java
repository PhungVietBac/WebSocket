package com.app.websocket.repository;

import com.app.websocket.model.Service;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ServiceRepository extends MongoRepository<Service, String> {
    // Lấy tất cả service của 1 user
    List<Service> findByUserId(String userId);
}
