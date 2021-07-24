package com.bootcamptrendxecom.notificationservice.service;

import com.bootcamptrendxecom.notificationservice.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserClient {
    private final RestTemplate template;
    @Value("${userApiAddress}")
    private String userServiceUrl;

    public UserClient(RestTemplate template) {
        this.template = template;
    }

    public User getUser(Long id){
        String url=userServiceUrl+"/user/?id="+id.toString();
        ResponseEntity<User> responseEntity=template.getForEntity(url,User.class);
        return responseEntity.getBody();
    }
}
