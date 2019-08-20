package com.favari.kafkapoc.http.datacontract;

import lombok.Data;

@Data
public class SkuDataContract {
    private String sku;
    private Integer quantity;
    private String description;
}
