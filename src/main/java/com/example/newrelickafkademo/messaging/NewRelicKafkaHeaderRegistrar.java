package com.example.newrelickafkademo.messaging;

import com.newrelic.api.agent.*;
import org.apache.kafka.common.header.Header;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class NewRelicKafkaHeaderRegistrar {
    public void registerHeaders(org.apache.kafka.common.header.Headers headers) {
        Headers dtHeaders = ConcurrentHashMapHeaders.build(HeaderType.MESSAGE);
        for (Header header : headers) {
            String headerValue = new String(header.value(), StandardCharsets.UTF_8);
            if (header.key().equals("newrelic")) {
                dtHeaders.addHeader("newrelic", headerValue);
            }
            if (header.key().equals("traceparent")) {
                dtHeaders.addHeader("traceparent", headerValue);
            }
            if (header.key().equals("tracestate")) {
                dtHeaders.addHeader("tracestate", headerValue);
            }
        }

        NewRelic.getAgent().getTransaction().acceptDistributedTraceHeaders(TransportType.Kafka, dtHeaders);
    }
}