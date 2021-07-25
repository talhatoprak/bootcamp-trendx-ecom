package com.trendx.ecomm.subscriptonservice.service;

import com.trendx.ecomm.subscriptonservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class UserClientService {
    @Autowired
    private RestTemplate template;
    @Value("${userApiAddress}")
    private String userServiceUrl;


    public User getUser(Long id){
        String url=userServiceUrl+"/user/"+id.toString();
        ResponseEntity<User> responseEntity=template.getForEntity(url,User.class);
        return responseEntity.getBody();
    }
}
