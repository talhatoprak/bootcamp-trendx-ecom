package com.trendx.ecom.service;

import com.trendx.ecom.entity.Product;
import com.trendx.ecom.entity.User;
import com.trendx.ecom.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProductService productService;

    public UserService(UserRepository userRepository, ProductService productService) {
        this.userRepository = userRepository;
        this.productService = productService;
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(
                String.format("Entity with id: %s does not exists.", id)
        ));
    }

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new RuntimeException("Email has already taken.");
        return userRepository.save(user);
    }

    public User subscribeToProduct(String userId, String productId) {
        Product product = this.productService.findById(productId);
        User user = this.findById(userId);
        user.followProduct(productId);
        return userRepository.save(user);
    }


}
