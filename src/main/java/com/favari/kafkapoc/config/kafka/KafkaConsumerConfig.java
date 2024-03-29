package com.favari.kafkapoc.config.kafka;

import java.util.HashMap;
import java.util.Map;

import com.favari.kafkapoc.entities.SkuCassandra;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.address}")
    private String address;

    public ConsumerFactory<String, String> consumerFactory(String groupId) {
        Map<String, Object> props = new HashMap<>();
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(String groupId) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(groupId));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> fooKafkaListenerContainerFactory() {
        return kafkaListenerContainerFactory("foo");
    }

    public ConsumerFactory<String, SkuCassandra> skuConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "sku");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(SkuCassandra.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SkuCassandra> skuKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SkuCassandra> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(skuConsumerFactory());
        return factory;
    }
}
