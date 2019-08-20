package com.favari.kafkapoc.usecases;

import com.favari.kafkapoc.entities.SkuCassandra;

public interface SkuUseCase {
    void send(SkuCassandra skuCassandra);
}
