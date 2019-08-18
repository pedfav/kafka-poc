package com.favari.kafkapoc.http.controller;

import com.favari.kafkapoc.entities.Sku;
import com.favari.kafkapoc.http.mapping.UrlMapping;
import com.favari.kafkapoc.usecases.SkuUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(UrlMapping.SKU)
public class SkuController {

    private final SkuUseCase skuUseCase;

    @ApiOperation("Insert sku on kafka")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "sku accepted")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> receiveSku(@RequestBody final Sku sku) {
        log.debug("endpoint=SkuController#receiveSku={}", sku);
        skuUseCase.send(sku);
        return ResponseEntity.ok().build();
    }
}
