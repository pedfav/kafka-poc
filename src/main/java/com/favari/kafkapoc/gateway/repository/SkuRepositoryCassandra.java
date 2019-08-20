package com.favari.kafkapoc.gateway.repository;

import com.favari.kafkapoc.entities.SkuCassandra;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuRepositoryCassandra extends CassandraRepository<SkuCassandra, String> {
}
