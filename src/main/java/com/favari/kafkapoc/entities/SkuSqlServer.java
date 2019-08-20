package com.favari.kafkapoc.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "inventory")
public class SkuSqlServer {

    @Id
    private String sku;
    private Integer quantity;
    private String description;
}
