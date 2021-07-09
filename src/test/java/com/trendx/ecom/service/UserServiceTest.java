package com.trendx.ecom.service;

import com.trendx.ecom.entity.Product;
import com.trendx.ecom.entity.User;
import com.trendx.ecom.repository.ProductRepository;
import com.trendx.ecom.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public UserServiceTest(@Autowired UserRepository userRepository,@Autowired ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Test
    public void findById() throws InterruptedException {
        //Given
        User user=new User();
        user.setEmail("t@t.com1");
        user.setFullName("Talha Toprak");
        userRepository.save(user);
        Thread.sleep(50);
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
        Thread.sleep(50);

        Product product=new Product("BR002","desc2",3,3.3);
        productRepository.save(product);

        user.followProduct(product.getId());
        userRepository.save(user);

        User user1=userRepository.findById(user.getId()).orElse(null);

        assertEquals(user1.getFollowedProducts().contains(product.getId()),true);
    }
}
