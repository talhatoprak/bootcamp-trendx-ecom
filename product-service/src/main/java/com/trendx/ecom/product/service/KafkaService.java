package com.trendx.ecom.product.service;

import com.trendx.ecom.product.kafkamodel.PriceChangeModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String msg, String topic){
        kafkaTemplate.send(topic, msg);
    }

    public void sendChangePriceMessage(PriceChangeModel msg, String topic){
        kafkaTemplate.send(topic, msg);
    }
}
