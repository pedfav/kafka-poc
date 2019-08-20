package com.favari.kafkapoc.gateway.repository;

import com.favari.kafkapoc.entities.SkuSqlServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuRepositorySqlServer extends JpaRepository<SkuSqlServer, String> {
}
