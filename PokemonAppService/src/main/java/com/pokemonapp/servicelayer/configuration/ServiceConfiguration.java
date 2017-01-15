package com.pokemonapp.servicelayer.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.pokemonapp.db.configuration.DataModelConfiguration;
import com.pokemonapp.servicelayer.service.ServiceComponents;

/**
 * Created by sarsovsk on 15.01.2017.
 *
 *  Spring configuration for the services.
 */
@Configuration
@Import(DataModelConfiguration.class)
@ComponentScan(basePackageClasses = { ServiceComponents.class})
public class ServiceConfiguration {

}
