package com.favari.kafkapoc.entities;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;

@Data
@Table("inventory")
public class SkuCassandra {

    @PrimaryKeyColumn(name = "sku", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String sku;

    private Integer quantity;
    private String description;

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }
}
