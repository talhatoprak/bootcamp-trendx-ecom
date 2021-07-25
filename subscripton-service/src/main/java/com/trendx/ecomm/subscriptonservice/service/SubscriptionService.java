package com.trendx.ecomm.subscriptonservice.service;

import com.trendx.ecomm.subscriptonservice.entity.Subscription;
import com.trendx.ecomm.subscriptonservice.model.*;
import com.trendx.ecomm.subscriptonservice.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SubscriptionService {
    private final SubscriptionRepository repository;
    private final UserClientService userClientService;
    private final ProductClientService productClientService;
    private final KafkaService kafkaService;

    public SubscriptionService(SubscriptionRepository repository, UserClientService userClientService, ProductClientService productClientService, KafkaService kafkaService) {
        this.repository = repository;
        this.userClientService = userClientService;
        this.productClientService = productClientService;
        this.kafkaService = kafkaService;
    }

    public Subscription getSubscriptionByProductId(String productId) {
        return repository.findById(productId).orElseThrow(RuntimeException::new);
    }
    public Set<String> getUsersByProductId(String productId){
        Subscription subscription=repository.findById(productId).orElse(null);
        if(subscription==null)
            return null;
        return subscription.getFollowerUserIds();
    }
    public Subscription followProduct(String userId, String productId) {
        Subscription subscription = repository.findById(productId)
            .orElse(new Subscription(productId, new HashSet<>()));

        subscription.followProduct(userId);
        return repository.save(subscription);
    }

    public Subscription unfollowProduct(String userId, String productId) {
        Subscription subscription = getSubscriptionByProductId(productId);
        subscription.unfollowProduct(userId);
        if (subscription.getFollowerUserIds().isEmpty()) {
            repository.deleteById(productId);
            return subscription;
        }
        return repository.save(subscription);
    }

    public List<Subscription> getAllSubscriptions() {
         List<Subscription> subscriptions = repository.findAll();
         return subscriptions;
    }

    public void deleteUserFromSubscriptionsSet(String userId) {
        List<Subscription> subscriptions = getAllSubscriptions();
        for(Subscription subscription:subscriptions) {
            subscription.unfollowProduct(userId);
            repository.save(subscription);
        }

    }

    public void listenChangeSalesPrice(PriceChangeModel model) {
        //get Product Info From Product Service
        Product product=productClientService.getProductById(model.getProductId());
        //getUsersByProductId
        Set<String> users=getUsersByProductId(model.getProductId());
        //sendNotification(user)
        for (String userId:users) {
            //GetUserInfo
            User user=userClientService.getUser(Long.parseLong(userId));
            if(user.getPlatform().equals("web") && model.getPlatform().equals("web")){
                String subject="Takip Ettiğiniz Ürünün Fiyatı Değişti";
                String content=String.format("Takip ettiğiniz %s ürünün fiyatı %s olarak değiştirildi", product.getBarcode(),model.getNewPrice());
                SendEmailModel notificationModel=new SendEmailModel(user.getEmail(),subject,content);
                System.out.println(notificationModel);
                kafkaService.sendMessage(notificationModel,"sendEmailNotification");
            }
            if(user.getPlatform().equals("mobile") && model.getPlatform().equals("mobile")){
                String title="Takip Ettiğiniz Ürünün Fiyatı Değişti";
                String content=String.format("Takip ettiğiniz %s ürünün fiyatı %s olarak değiştirildi", product.getBarcode(),model.getNewPrice());
                SendNotificationModel notificationModel=new SendNotificationModel(user.getId(),title,content);
                System.out.println(notificationModel);
                kafkaService.sendMessage(notificationModel,"sendMobileNotification");
            }
        }
    }

    public void deleteProduct(String productId) {
        repository.deleteById(productId);
    }
}
