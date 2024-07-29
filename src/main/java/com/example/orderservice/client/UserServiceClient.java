package com.example.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userservice", url = "http://localhost:8080")
public interface UserServiceClient {

    @GetMapping("/users/{id}")
    UserResponse getUserById(@PathVariable("id") Long id);

}