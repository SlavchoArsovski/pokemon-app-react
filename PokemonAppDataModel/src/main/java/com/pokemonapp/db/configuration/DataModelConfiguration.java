package com.pokemonapp.db.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pokemonapp.db.repository.RepositoryComponents;

/**
 * Created by sarsovsk on 15.01.2017.
 *
 * Spring configuration for the data model.
 */
@Configuration
@Import({
    JpaPropertiesProvider.class,
    BasicDataSourceProvider.class,
    EntityManagerFactoryProvider.class,
    JpaTransactionManagerProvider.class
})
@ComponentScan(
    basePackageClasses = {
        RepositoryComponents.class
    })
@EnableJpaRepositories(basePackageClasses = { RepositoryComponents.class })
@EnableTransactionManagement
public class DataModelConfiguration {

}
