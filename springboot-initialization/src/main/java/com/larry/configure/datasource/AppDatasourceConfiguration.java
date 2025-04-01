package com.larry.configure.datasource;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.larry.security.persistence.repository"
        },
        entityManagerFactoryRef = "appEntityManagerFactory",
        transactionManagerRef = "appTransactionManager"
)
public class AppDatasourceConfiguration {
    @Primary
    @QuartzDataSource
    @Bean(name="appDataSource")
    @ConfigurationProperties(prefix = "app.app-datasource")
    public DataSource getDataSource(){
        return new HikariDataSource();
    }

    @Primary
    @Bean(name="appEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(final EntityManagerFactoryBuilder builder,
                                                                          final @Qualifier("appDataSource") DataSource ds){
        log.info(((HikariDataSource) ds).getPoolName() + " pool created.");
        log.info("Maximum Pool Size: "+((HikariDataSource) ds).getMaximumPoolSize());
        log.info("MinimumIdle: "+((HikariDataSource) ds).getMinimumIdle());
        return builder.
                dataSource(ds).
                packages("com.larry.persistence", "com.larry.security.persistence").
                persistenceUnit("appPU").
                build();
    }
    @Primary
    @QuartzTransactionManager
    @Bean(name="appTransactionManager")
    public PlatformTransactionManager getTransactionManager(@Qualifier("appEntityManagerFactory") EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager(emf);
        transactionManager.setDefaultTimeout(120);
        return transactionManager;
    }
}
