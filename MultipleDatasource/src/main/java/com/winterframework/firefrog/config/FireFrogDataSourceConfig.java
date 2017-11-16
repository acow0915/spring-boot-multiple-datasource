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
import org.springframework.context.annotation.Primary;
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
@EntityScan(basePackages={"com.winterframework.firefrog.entity"})
@EnableJpaRepositories(
        entityManagerFactoryRef="oracleEntityManagerFactory",
        transactionManagerRef="oracleTransactionManager",
        basePackages= { "com.winterframework.firefrog.dao" }) //设置Repository所在位置
public class FireFrogDataSourceConfig {
	
	/**oracle Url*/
	@Value("${oracle.jdbc.url}")
	private String oracleUrl;

	/**oracle 帳號*/
	@Value("${oracle.jdbc.username}")
	private String oracleUserName;
	
	/**oracle 密碼*/
	@Value("${oracle.jdbc.passworld}")
	private String oracleUserPassword;
	
	@Autowired
    private JpaProperties jpaProperties;
	
	@Primary
	@Bean(name="oracleDataSource")
	public DataSource createOracleDataSource(){
		
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		datasource.setUrl(oracleUrl);
		datasource.setUsername(oracleUserName);
		datasource.setPassword(oracleUserPassword);
		return datasource;
	}
	
	@Primary
	@Qualifier(value = "oracleEntityManagerFactory")
	@Bean(name = "oracleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(createOracleDataSource())
                .properties(jpaProperties.getHibernateProperties(createOracleDataSource()))
                .packages("com.winterframework.firefrog.entity") //设置实体类所在位置
                .persistenceUnit("default")  // 指定persistence unit
                .build();
    }
	
	@Primary
	@Qualifier(value = "oracleEntityManager")
	@Bean(name = "oracleEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }
	
	@Primary
	@Qualifier(value = "oracleTransactionManager")
	@Bean(name = "oracleTransactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory); // 构建事务管理器
    }
	
}
