package com.pokemonapp.db.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import net.sf.ehcache.hibernate.EhCacheRegionFactory;

import static java.lang.String.valueOf;

import static org.hibernate.cfg.AvailableSettings.*;
import static org.hibernate.jpa.AvailableSettings.NAMING_STRATEGY;

/**
 * Created by sarsovsk on 15.01.2017.
 *
 * Spring configuration.
 * Provides JPA properties to be used with the {@link javax.persistence.EntityManager}.
 */
@Configuration
public class JpaPropertiesProvider {

  @Value("${jpa.properties.resource:}")
  private Resource resource;

  @Value("${hibernate.dialect}")
  private String hibernateDialect;

  @Value("${hibernate.default_schema}")
  private String hibernateDefaultSchema;

  @Value("${hibernate.default_batch_fetch_size:16}")
  private int hibernateDefaultBatchFetchSize;

  @Value("${hibernate.show_sql:false}")
  private boolean hibernateShowSql;

  @Value("${hibernate.format_sql:false}")
  private boolean hibernateFormatSql;

  @Value("${hibernate.generate_statistics:false}")
  private boolean hibernateGenerateStatistics;

  @Value("${hibernate.session.events.log:false}")
  private boolean hibernateSessionEventsLog;

  @Value("${hibernate.use_sql_comments:false}")
  private boolean hibernateUseSqlComments;

  @Value("${hibernate.hbm2ddl.auto:}")
  private String hibernateHbm2ddlAuto;

  @Value("${hibernate.hbm2ddl.import_files:/import.sql}")
  private String hibernateHbm2ddlImportFiles;

  @Value("${hibernate.ejb.naming_strategy:org.hibernate.cfg.ImprovedNamingStrategy}")
  private String hibernateEjbNamingStrategy;

  @Value("${hibernate.cache.region.factory_class:org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory}")
  private String hibernateCacheRegionFactoryClass;

  @Value("${hibernate.cache.use_second_level_cache:true}")
  private boolean hibernateCacheUseSecondLevelCache;

  @Value("${hibernate.cache.use_query_cache:false}")
  private boolean hibernateCacheUseQueryCache;

  @Value("${hibernate.cache.configurationResourceName:ehcache-orm-default.xml}")
  private String hibernateCacheConfigurationResourceName;

  @Value("${jadira.usertype.autoRegisterUserTypes:true}")
  private boolean jadiraUserTypeAutoRegisterUserTypes;

  @Value("${jadira.usertype.databaseZone:UTC}")
  private String jadiraUserTypeDatabaseZone;

  @Value("${jadira.usertype.javaZone:jvm}")
  private String jadiraUserTypeJavaZone;

  /**
   * @return the JPA properties
   * @throws IOException in case of I/O error during resource loading
   */
  @Bean
  public Properties јpaProperties() throws IOException {
    Properties properties = new Properties();

    if (resource != null) {
      InputStream is = null;
      try {
        is = resource.getInputStream();
        properties.load(is);
      } finally {
        if (is != null) {
          is.close();
        }
      }
    }

    properties.put(DIALECT, hibernateDialect);

    properties.put(DEFAULT_SCHEMA, hibernateDefaultSchema);
    properties.put(DEFAULT_BATCH_FETCH_SIZE, valueOf(hibernateDefaultBatchFetchSize));

    properties.put(SHOW_SQL, valueOf(hibernateShowSql));
    properties.put(FORMAT_SQL, valueOf(hibernateFormatSql));
    properties.put(GENERATE_STATISTICS, valueOf(hibernateGenerateStatistics));
    properties.put(LOG_SESSION_METRICS, valueOf(hibernateSessionEventsLog));
    properties.put(USE_SQL_COMMENTS, valueOf(hibernateUseSqlComments));

    properties.put(HBM2DDL_AUTO, hibernateHbm2ddlAuto);
    properties.put(HBM2DDL_IMPORT_FILES, hibernateHbm2ddlImportFiles);

    properties.put(NAMING_STRATEGY, hibernateEjbNamingStrategy);

    properties.put(CACHE_REGION_FACTORY, hibernateCacheRegionFactoryClass);
    properties.put(USE_SECOND_LEVEL_CACHE, valueOf(hibernateCacheUseSecondLevelCache));
    properties.put(USE_QUERY_CACHE, valueOf(hibernateCacheUseQueryCache));

    properties.put(
        EhCacheRegionFactory.NET_SF_EHCACHE_CONFIGURATION_RESOURCE_NAME,
        hibernateCacheConfigurationResourceName);

    // keys can be found here: org.jadira.usertype.spi.shared.AbstractUserTypeHibernateIntegrator
    // they are private so we have to resort to string constants...
    properties.put("jadira.usertype.autoRegisterUserTypes",
        valueOf(jadiraUserTypeAutoRegisterUserTypes));
    properties.put("jadira.usertype.databaseZone", jadiraUserTypeDatabaseZone);
    properties.put("jadira.usertype.javaZone", jadiraUserTypeJavaZone);

    return properties;
  }

}
