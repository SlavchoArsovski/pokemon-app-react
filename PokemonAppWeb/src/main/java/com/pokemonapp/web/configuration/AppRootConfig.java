package com.pokemonapp.web.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;

import com.pokemonapp.rest.configuration.RestConfiguration;
import com.pokemonapp.servicelayer.configuration.ServiceConfiguration;

/**
 * Created by sarsovsk on 15.01.2017.
 *
 * App root spring configuration.
 */
@Configuration
@Import({ ServiceConfiguration.class, SecurityConfig.class })
public class AppRootConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {

        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();

        Resource resource = applicationContext.getResource("classpath:config.properties");
        propertyPlaceholderConfigurer.setLocations(resource);

        return propertyPlaceholderConfigurer;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
