package com.example.springbootmicroservicestarter.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {
    @KafkaListener(topics = "demo-topic", groupId = "demo-consumer-group")
    public void listen(String message) {
        System.out.println("Received: " + message);
    }
}
