package com.pokemonapp.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.pokemonapp.rest.configuration.RestConfiguration;
import com.pokemonapp.web.controller.ControllerComponents;
import com.pokemonapp.web.validator.ValidatorComponents;

/**
 * Created by sarsovsk on 15.01.2017.
 * <p>
 * Spring Web configuration.
 */
@EnableWebMvc
@Configuration
@Import(RestConfiguration.class)
@ComponentScan(basePackageClasses = {ControllerComponents.class, ValidatorComponents.class})
public class WebConfig extends WebMvcConfigurerAdapter {

  public static final String VIEWS_PATH = "/WEB-INF/views/";

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    registry.addResourceHandler("/images/**").addResourceLocations("/images/");
  }

  @Bean
  public InternalResourceViewResolver viewResolver() {

    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix(VIEWS_PATH);
    viewResolver.setSuffix(".jsp");

    return viewResolver;
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    super.addCorsMappings(registry);
    registry
        .addMapping("/**")
        .allowedMethods("GET", "POST", "DELETE", "PUT")
        .allowCredentials(false);
  }
}
