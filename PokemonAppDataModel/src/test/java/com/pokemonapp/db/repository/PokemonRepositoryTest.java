package com.pokemonapp.db.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pokemonapp.db.configuration.BasicDataSourceProvider;
import com.pokemonapp.db.configuration.DataModelConfiguration;
import com.pokemonapp.db.configuration.EntityManagerFactoryProvider;
import com.pokemonapp.db.configuration.JpaPropertiesProvider;
import com.pokemonapp.db.configuration.JpaTransactionManagerProvider;
import com.pokemonapp.db.datamodel.Pokemon;

/**
 * Created by sarsovsk on 15.01.2017.
 * <p>
 * Contains tests for {@link PokemonRepository}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PokemonRepositoryTest {

  @Configuration
  @Import({
      DataModelConfiguration.class
  })
  public static class TestConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {

      PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();

      Resource resource = applicationContext.getResource("classpath:config-test.properties");
      propertyPlaceholderConfigurer.setLocations(resource);

      return propertyPlaceholderConfigurer;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      this.applicationContext = applicationContext;
    }
  }

  @Autowired
  private PokemonRepository pokemonRepository;

  @Test
  public void test() {

    List<Pokemon> all = pokemonRepository.findAll();

    System.out.println(all.size());

  }

}