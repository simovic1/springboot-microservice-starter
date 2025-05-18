package com.example.springbootmicroservicestarter.controller;

import com.example.springbootmicroservicestarter.kafka.KafkaMessageProducer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/kafka")
@Tag(name = "Kafka Messaging", description = "Kafka message producer endpoint")
public class KafkaController {
    private final KafkaMessageProducer producer;

    public KafkaController(KafkaMessageProducer producer) {
        this.producer = producer;
    }

    @Operation(
            summary = "Send message to Kafka",
            description = "Produces a message to the Kafka topic 'demo-topic'"
    )
    @GetMapping("/send")
    public String sendMessage(
            @Parameter(description = "Message to send", example = "hello world")
            @RequestParam String message
    ) {
        System.out.println("Producing the message " + message);
        producer.sendMessage(message);
        return "Message sent " + message;
    }
}
