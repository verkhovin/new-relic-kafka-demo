package com.example.newrelickafkademo.messaging;

import com.newrelic.api.agent.Trace;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final static Logger log = LoggerFactory.getLogger(Consumer.class);

    private final NewRelicKafkaHeaderRegistrar newRelicKafkaHeaderRegistrar;

    public Consumer(NewRelicKafkaHeaderRegistrar newRelicKafkaHeaderRegistrar) {
        this.newRelicKafkaHeaderRegistrar = newRelicKafkaHeaderRegistrar;
    }

    @KafkaListener(topics = "example")
    @Trace(dispatcher = true)
    public void onMessage(ConsumerRecord<String, String> record) {
        final Headers headers = record.headers();
        newRelicKafkaHeaderRegistrar.registerHeaders(headers);
        log.info("Message consumed with body {} and following headers: ", record.value());
        headers.forEach(header -> log.info("Header name: {}", header.key()));
    }
}
