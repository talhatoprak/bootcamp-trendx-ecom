package com.trendx.ecom.controller;

import com.trendx.ecom.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.trendx.ecom.entity.User;

@RestController
public class UserController {
    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public User findById(String id) {
        return userService.findById(id);
    }

    @PutMapping("/subscribe")
    public User subscribeToProduct(@RequestParam String userId, @RequestParam String productId) {
        return userService.subscribeToProduct(userId, productId);
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
