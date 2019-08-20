package com.favari.kafkapoc.usecases.impl;

import com.favari.kafkapoc.entities.SkuCassandra;
import com.favari.kafkapoc.gateway.repository.SkuRepositoryCassandra;
import com.favari.kafkapoc.usecases.PerformanceTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CassandraPerformanceTestImpl implements PerformanceTest {

    private final SkuRepositoryCassandra skuRepositoryCassandra;

    public String performanceTest(Integer times){
        List<SkuCassandra> skuCassandraList = new ArrayList<>();

        for (int i = 0; i < times; i++) {
            SkuCassandra skuCassandra = new SkuCassandra();
            skuCassandra.setSku("sku" + i);
            skuCassandra.setDescription("desc" + i);
            skuCassandra.setQuantity(i);

            skuCassandraList.add(skuCassandra);
        }

        long startTime = Instant.now().toEpochMilli();

        skuRepositoryCassandra.insert(skuCassandraList);

        long finalTime = Instant.now().toEpochMilli();

        return "Time Elapsed = " + (finalTime - startTime);
    }
}
