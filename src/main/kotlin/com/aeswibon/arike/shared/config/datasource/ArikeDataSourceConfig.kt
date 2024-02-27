package com.aeswibon.arike.shared.config.datasource

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.aeswibon.arike"],
    entityManagerFactoryRef = "arikeEntityManager",
    transactionManagerRef = "arikeTransactionManager"
)
class ArikeDatasourceConfig {
    @Value("\${spring.datasource.driver-class-name}")
    private lateinit var driverClassName: String

    @Value("\${spring.datasource.url}")
    private lateinit var database: String

    @Value("\${spring.datasource.username}")
    private lateinit var username: String

    @Value("\${spring.datasource.password}")
    private lateinit var password: String

    @Bean
    @Primary
    fun arikeEntityManager(): LocalContainerEntityManagerFactoryBean {
        val entityManager = LocalContainerEntityManagerFactoryBean()
        entityManager.dataSource = arikeDataSource()
        entityManager.setPackagesToScan(
            "com.aeswibon.arike"
        )
        val vendorAdapter = HibernateJpaVendorAdapter()
        entityManager.jpaVendorAdapter = vendorAdapter
        return entityManager
    }

    @Bean
    @Primary
    fun arikeDataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(driverClassName)
        dataSource.url = database
        dataSource.username = username
        dataSource.password = password
        return dataSource
    }

    @Bean
    @Primary
    fun arikeTransactionManager(): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = arikeEntityManager().getObject()
        return transactionManager
    }
}