package com.winterframework.firefrog.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(ignoreResourceNotFound = false, value = {
		"classpath:config/getway-${profiles.active}.properties"
})
@EntityScan(basePackages={"com.winterframework.fhx.entity"})
@EnableJpaRepositories(
        entityManagerFactoryRef="fhxEntityManagerFactory",
        transactionManagerRef="fhxTransactionManager",
        basePackages= { "com.winterframework.fhx.dao" }) //设置Repository所在位置
public class FhxDataSourceConfig {

	/**fhx Url*/
	@Value("${mysql.fhx.jdbc.url}")
	private String fhxUrl;

	/**fhx 帳號*/
	@Value("${mysql.fhx.jdbc.username}")
	private String fhxUserName;
	
	/**fhx 密碼*/
	@Value("${mysql.fhx.jdbc.passworld}")
	private String fhxUserPassword;
	
	@Autowired
    private JpaProperties jpaProperties;
	
	@Bean(name="fhxDataSource")
	public DataSource createPtDataSource(){
		
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl(fhxUrl);
		datasource.setUsername(fhxUserName);
		datasource.setPassword(fhxUserPassword);
		return datasource;
	}
	
	@Qualifier(value = "fhxEntityManagerFactory")
	@Bean(name = "fhxEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(createPtDataSource())
                .properties(jpaProperties.getHibernateProperties(createPtDataSource()))
                .packages("com.winterframework.fhx.entity") //设置实体类所在位置
                .persistenceUnit("default")  // 指定persistence unit
                .build();
    }
	
	@Qualifier(value = "fhxEntityManager")
	@Bean(name = "fhxEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }
	
	@Qualifier(value = "fhxTransactionManager")
	@Bean(name = "fhxTransactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory); // 构建事务管理器
    }
}
