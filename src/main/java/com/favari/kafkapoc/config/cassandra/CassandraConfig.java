package com.favari.kafkapoc.config.cassandra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Objects;

@Slf4j
@Configuration
@PropertySource(value = { "classpath:application.yml" })
@EnableCassandraRepositories(basePackages = "com.favari.kafkapoc.gateway.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Autowired
    private Environment environment;

    @Override
    protected String getKeyspaceName() {
        return environment.getProperty("spring.data.cassandra.keyspace");
    }

    @Override
    @Bean
    public CassandraClusterFactoryBean cluster() {
        final CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(environment.getProperty("spring.data.cassandra.contactpoints"));
        cluster.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("spring.data.cassandra.port"))));
        log.info("Cluster created with contact points [" + environment.getProperty("spring.data.cassandra.contactpoints") + "] " + "& port [" + Integer.parseInt(Objects.requireNonNull(environment.getProperty("spring.data.cassandra.port"))) + "].");
        return cluster;
    }

    @Override
    protected boolean getMetricsEnabled() { return false; }

    @Override
    @Bean
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        return new BasicCassandraMappingContext();
    }
}