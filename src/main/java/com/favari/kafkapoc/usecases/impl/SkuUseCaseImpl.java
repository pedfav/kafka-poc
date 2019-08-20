package com.favari.kafkapoc.usecases.impl;

import com.favari.kafkapoc.config.kafka.MessageProducer;
import com.favari.kafkapoc.entities.SkuCassandra;
import com.favari.kafkapoc.usecases.SkuUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SkuUseCaseImpl implements SkuUseCase {

    private final MessageProducer producer;

    public void send(SkuCassandra skuCassandra){
        producer.sendSku(skuCassandra);
    }
}
