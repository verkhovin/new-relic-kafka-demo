package com.example.newrelickafkademo.messaging;

import com.newrelic.api.agent.Trace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Producer {
    private final static Logger log = LoggerFactory.getLogger(Producer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Trace(dispatcher = true)
    public void produceMessage() {
        var message = UUID.randomUUID().toString();
        log.info("Producing message: \"{}\"", message);
        kafkaTemplate.send("example", message);
    }
}
