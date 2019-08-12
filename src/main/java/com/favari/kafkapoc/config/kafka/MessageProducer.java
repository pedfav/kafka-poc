package com.favari.kafkapoc.config.kafka;

import com.favari.kafkapoc.entities.Sku;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@RequiredArgsConstructor
public class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaTemplate<String, Sku> skuKafkaTemplate;

    @Value(value = "${app.topic.sku}")
    private String topicSku;

    @Value(value = "${app.topic.message}")
    private String topicMessage;

    public void sendMessage(String message) {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicMessage, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }
    public void sendSku(Sku sku) {
        skuKafkaTemplate.send(topicSku, buildKey(sku), sku);
    }

    private String buildKey(final Sku sku) {
        return String.format("sku=%s", sku.getSku());
    }
}