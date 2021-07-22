package com.trendx.ecom.service;

import com.trendx.ecom.entity.Product;
import com.trendx.ecom.entity.User;
import com.trendx.ecom.repository.ProductRepository;
import com.trendx.ecom.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Autowired
    public UserServiceTest(UserRepository userRepository,
                           ProductRepository productRepository,
                           UserService userService) {

        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Test
    public void findById() {
        //Given
        User user=new User();
        user.setEmail("t@t.com1");
        user.setFullName("Talha Toprak");
        userRepository.save(user);
        System.out.println("userServiceTest:findById");

        //When
        User user1 = userRepository.findById(user.getId()).orElse(null);

        //Then
        assertEquals("Talha Toprak", user1.getFullName());
    }

    @Test
    public void followProduct() throws InterruptedException {
        //Given
        User user = new User("y@y.com","Yavuz Bilgin");
        userRepository.save(user);

        Product product = new Product("BR002","desc2",3,3.3);
        productRepository.save(product);

        //When
        User foundUser = userService.subscribeToProduct(user.getId(), product.getId());

        //Then
        assertTrue(foundUser.getFollowedProducts().contains(product.getId()));
    }

    @Test
    public void createUser() {
        //Given
        User user = new User("y@y.com","Yavuz Bilgin");

        //When
        User savedUser = userService.createUser(user);
        User foundUser = userRepository.findById(savedUser.getId()).orElseThrow();

        //Then
        assertEquals(savedUser.getEmail(), foundUser.getEmail());
    }
}
