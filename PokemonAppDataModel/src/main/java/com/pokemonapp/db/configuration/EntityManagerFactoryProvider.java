package com.pokemonapp.db.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.PersistenceProvider;
import javax.sql.DataSource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import static com.google.common.base.Splitter.on;
import static com.google.common.base.Strings.emptyToNull;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Iterables.toArray;

/**
 * Created by sarsovsk on 15.01.2017.
 *
 * Spring configuration.
 * Provides an {@link EntityManagerFactory}.
 */
@Configuration
public class EntityManagerFactoryProvider {

  @Autowired
  private DataSource dataSource;

  @Autowired
  @Qualifier("јpaProperties")
  private Properties jpaProperties;

  @Value("${jpa.persistenceProvider:org.hibernate.jpa.HibernatePersistenceProvider}")
  private String persistenceProviderClassName;

  @Value("${jpa.vendorAdapter:org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter}")
  private String jpaVendorAdapterClassName;

  @Value("${jpa.dialect:org.springframework.orm.jpa.vendor.HibernateJpaDialect}")
  private String jpaDialectClassName;

  @Value("${jpa.persistenceUnitName:default}")
  private String persistenceUnitName;

  @Value("${jpa.packagesToScan:}")
  private String packagesToScan;

  /** see {@link ValidationMode} */
  @Value("${javax.persistence.validation.mode:AUTO}")
  private String javaxPersistenceValidationMode;

  /** see {@link SharedCacheMode} */
  @Value("${javax.persistence.sharedCache.mode:UNSPECIFIED}")
  private String javaxPersistenceSharedCacheMode;

  /**
   * @return the entity manager factory
   * @throws ClassNotFoundException if a configured class can't be located
   */
  @Bean
  public FactoryBean<EntityManagerFactory> entityManagerFactory() throws ClassNotFoundException {

    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

    entityManagerFactory.setDataSource(dataSource);

    entityManagerFactory.setPersistenceProvider((PersistenceProvider) instantiate(persistenceProviderClassName));
    entityManagerFactory.setJpaVendorAdapter((JpaVendorAdapter) instantiate(jpaVendorAdapterClassName));
    entityManagerFactory.setJpaDialect((JpaDialect) instantiate(jpaDialectClassName));

    entityManagerFactory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
    entityManagerFactory.setPersistenceUnitName(emptyToNull(persistenceUnitName));

    entityManagerFactory.setPackagesToScan(determinePackagesToScan());

    entityManagerFactory.setValidationMode(ValidationMode.valueOf(javaxPersistenceValidationMode));
    entityManagerFactory.setSharedCacheMode(SharedCacheMode.valueOf(javaxPersistenceSharedCacheMode));

    entityManagerFactory.setJpaProperties(jpaProperties);

    return entityManagerFactory;
  }

  private Object instantiate(String className) throws ClassNotFoundException {
    if (isNullOrEmpty(className)) {
      return null;
    }
    Class<?> clazz = Class.forName(className);
    return BeanUtils.instantiate(clazz);
  }

  private String[] determinePackagesToScan() {
    if (isNullOrEmpty(packagesToScan)) {
      return null;
    }
    return toArray(on(',').trimResults().split(packagesToScan), String.class);
  }

}
