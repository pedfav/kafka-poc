package com.favari.kafkapoc.gateway.kafka;

import com.favari.kafkapoc.entities.SkuCassandra;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Listener {
    @KafkaListener(topics = "${app.topic.sku}", containerFactory = "skuKafkaListenerContainerFactory")
    public void skuListener(SkuCassandra skuCassandra) {
        log.info("Received sku: " + skuCassandra);
    }

    @KafkaListener(topics = "${app.topic.message}", containerFactory = "fooKafkaListenerContainerFactory")
    public void messageListener(String message) {
        log.info("Received message: " + message);
    }
}
