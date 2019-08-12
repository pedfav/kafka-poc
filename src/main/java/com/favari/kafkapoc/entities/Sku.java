package com.favari.kafkapoc.entities;

import lombok.Data;
import java.util.Objects;

@Data
public class Sku {
    private String sku;
    private Integer quantity;
    private String desc;

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }
}
