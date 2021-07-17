package com.trendx.ecom.controller;

import com.trendx.ecom.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.trendx.ecom.entity.User;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findbyid")
    public User findById(@RequestParam String id) {
        System.out.println(String.format("UserController :: findById :: UserId = %s",id));
        return userService.findById(id);
    }

    @PutMapping("/subscribe")
    public User subscribeToProduct(@RequestParam String userId, @RequestParam String productId) {
        System.out.println(String.format("UserController :: subscribeToProduct :: UserId : %s , productId : %s ",userId,productId));
        return userService.subscribeToProduct(userId, productId);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        System.out.println("UserController :: createUser :: "+user.toString());
        return userService.createUser(user);
    }
    @PostMapping("/delete")
    public void deleteById(@RequestParam String id)
    {
        System.out.println(String.format("UserController :: deleteById :: UserId = %s",id));
        userService.deleteById(id);
    }
}
