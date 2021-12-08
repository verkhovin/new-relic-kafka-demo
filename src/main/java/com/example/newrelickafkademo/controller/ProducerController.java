package com.example.newrelickafkademo.controller;

import com.example.newrelickafkademo.messaging.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProducerController {
    private final Producer producer;

    ProducerController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping
    public void produce() {
        producer.produceMessage();
    }
}