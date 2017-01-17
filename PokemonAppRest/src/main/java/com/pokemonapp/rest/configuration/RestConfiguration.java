package com.pokemonapp.rest.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.pokemonapp.rest.controller.RestComponents;

/**
 * Created by sarsovsk on 15.01.2017.
 *
 * App root spring configuration.
 */
@Configuration
@ComponentScan(basePackageClasses = RestComponents.class)
public class RestConfiguration {

}
