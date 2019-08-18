package com.favari.kafkapoc.usecases;

import com.favari.kafkapoc.config.kafka.MessageProducer;
import com.favari.kafkapoc.entities.Sku;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SkuUseCase {

    private final MessageProducer producer;

    public void send(Sku sku){
        producer.sendSku(sku);
    }
}
