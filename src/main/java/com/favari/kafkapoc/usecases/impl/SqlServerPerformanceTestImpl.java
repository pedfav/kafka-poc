package com.favari.kafkapoc.usecases.impl;

import com.favari.kafkapoc.entities.SkuSqlServer;
import com.favari.kafkapoc.gateway.repository.SkuRepositorySqlServer;
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
public class SqlServerPerformanceTestImpl implements PerformanceTest {

    private final SkuRepositorySqlServer skuRepositorySqlServer;

    @Override
    public String performanceTest(Integer times) {
        List<SkuSqlServer> skuSqlServers = new ArrayList<>();

        for (int i = 0; i < times; i++) {
            SkuSqlServer skuSqlServer = new SkuSqlServer();
            skuSqlServer.setSku("sku" + i);
            skuSqlServer.setDescription("desc" + i);
            skuSqlServer.setQuantity(i);

            skuSqlServers.add(skuSqlServer);
        }

        long startTime = Instant.now().toEpochMilli();

        skuRepositorySqlServer.saveAll(skuSqlServers);

        long finalTime = Instant.now().toEpochMilli();

        return "Time Elapsed = " + (finalTime - startTime);
    }
}
