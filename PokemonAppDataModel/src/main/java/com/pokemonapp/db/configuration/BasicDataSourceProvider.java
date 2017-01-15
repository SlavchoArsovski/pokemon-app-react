package com.pokemonapp.db.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Created by sarsovsk on 15.01.2017.
 *
 * Spring configuration.
 * Provider for a {@link DataSource}.
 */
@Configuration
public class BasicDataSourceProvider {

  @Bean
  public DataSource dataSource() {

    // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    EmbeddedDatabase db = builder
        .setType(EmbeddedDatabaseType.HSQL)
        .build();

    return db;
  }

}
