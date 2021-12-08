package com.example.newrelickafkademo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {
    @Bean
    public NewTopic exampleTopic() {
        return new NewTopic("example", 1, (short) 1);
    }
}
