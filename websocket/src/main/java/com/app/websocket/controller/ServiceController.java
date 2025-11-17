package com.app.websocket.controller;

import com.app.websocket.model.Service;
import com.app.websocket.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    // GET /api/services -> lấy tất cả services
    @GetMapping
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    // GET /api/services/{id} -> lấy chi tiết 1 service
    @GetMapping("/{id}")
    public Service getServiceById(@PathVariable String id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Service not found"
                ));
    }

    // GET /api/services/user/{userId} -> lấy tất cả service của 1 user
    @GetMapping("/user/{userId}")
    public List<Service> getServicesByUser(@PathVariable String userId) {
        return serviceRepository.findByUserId(userId);
    }

    // POST /api/services -> tạo service mới
    @PostMapping
    public Service createService(@RequestBody Service service) {
        if (service.getCreatedAt() == null) {
            service.setCreatedAt(Instant.now());
        }
        service.setId(null);
        // nếu status null thì set mặc định
        if (service.getStatus() == null) {
            service.setStatus("ACTIVE");
        }
        return serviceRepository.save(service);
    }

    // PUT /api/services/{id} -> cập nhật service
    @PutMapping("/{id}")
    public Service updateService(@PathVariable String id, @RequestBody Service serviceRequest) {
        Service existing = serviceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Service not found"
                ));

        existing.setName(serviceRequest.getName());
        existing.setIpv4Address(serviceRequest.getIpv4Address());
        existing.setStatus(serviceRequest.getStatus());
        existing.setUserId(serviceRequest.getUserId());

        return serviceRepository.save(existing);
    }

    // DELETE /api/services/{id} -> xóa service
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(@PathVariable String id) {
        if (!serviceRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Service not found"
            );
        }
        serviceRepository.deleteById(id);
    }
}
