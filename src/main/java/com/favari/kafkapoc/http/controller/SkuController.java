package com.favari.kafkapoc.http.controller;

import com.favari.kafkapoc.entities.SkuCassandra;
import com.favari.kafkapoc.usecases.impl.CassandraPerformanceTestImpl;
import com.favari.kafkapoc.gateway.repository.SkuRepositoryCassandra;
import com.favari.kafkapoc.http.datacontract.SkuDataContract;
import com.favari.kafkapoc.http.mapping.UrlMapping;
import com.favari.kafkapoc.usecases.SkuUseCase;
import com.favari.kafkapoc.usecases.impl.SqlServerPerformanceTestImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(UrlMapping.SKU)
public class SkuController {

    private final SkuUseCase skuUseCase;
    private final SkuRepositoryCassandra skuRepositoryCassandra;
    private final CassandraPerformanceTestImpl cassandraPerformanceTest;
    private final SqlServerPerformanceTestImpl sqlServerPerformanceTest;

    @ApiOperation("Insert sku on kafka")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "sku accepted")
    })
    @PostMapping(value="/kafka", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> receiveSkuKafka(@RequestBody final SkuDataContract skuDataContract) {
        log.debug("endpoint=SkuController#receiveSkuKafka={}", skuDataContract);
        SkuCassandra skuCassandra = new SkuCassandra();
        BeanUtils.copyProperties(skuDataContract, skuCassandra);
        skuUseCase.send(skuCassandra);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Insert sku on cassandra")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "sku accepted")
    })
    @PostMapping(value="/cassandra", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> receiveSkuCassandra(@RequestBody final SkuDataContract skuDataContract) {
        log.debug("endpoint=SkuController#receiveSkuCassandra={}", skuDataContract);
        SkuCassandra skuCassandra = new SkuCassandra();
        BeanUtils.copyProperties(skuDataContract, skuCassandra);
        skuRepositoryCassandra.insert(skuCassandra);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Insert sku on cassandra")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "sku accepted")
    })
    @GetMapping(value="/cassandra/{times}")
    public ResponseEntity<String> performanceTestCassandra(@PathVariable Integer times) {
        log.debug("endpoint=SkuController#performanceTestCassandra={}", times);
        return ResponseEntity.ok(cassandraPerformanceTest.performanceTest(times));
    }

    @ApiOperation("Insert sku on cassandra")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "sku accepted")
    })
    @GetMapping(value="/sqlserver/{times}")
    public ResponseEntity<String> performanceTestSqlServer(@PathVariable Integer times) {
        log.debug("endpoint=SkuController#performanceTestSqlServer={}", times);
        return ResponseEntity.ok(sqlServerPerformanceTest.performanceTest(times));
    }
}
