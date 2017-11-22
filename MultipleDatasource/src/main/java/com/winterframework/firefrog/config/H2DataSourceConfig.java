package com.winterframework.firefrog.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource(ignoreResourceNotFound = false, value = {
		"classpath:config/getway-${profiles.active}.properties"
})
public class H2DataSourceConfig {

	/**h2 Url*/
	@Value("${h2.jdbc.url}")
	private String h2Url;

	/**h2 帳號*/
	@Value("${h2.jdbc.username}")
	private String h2UserName;
	
	/**h2 密碼*/
	@Value("${h2.jdbc.passworld}")
	private String h2UserPassword;
	
	@Primary
	@Bean(name="h2DataSource")
	public DataSource createH2DataSource(){
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("org.h2.Driver");
		datasource.setUrl(h2Url);
		datasource.setUsername(h2UserName);
		datasource.setPassword(h2UserPassword);
		return datasource;
	}
	
//	@Primary
//	@Qualifier(value = "h2EntityManagerFactory")
//	@Bean(name = "h2EntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary (EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(createH2DataSource())
//                .properties(jpaProperties.getHibernateProperties(createH2DataSource()))
//                .packages("com.winterframework.pt.entity") //设置实体类所在位置
//                .persistenceUnit("default")  // 指定persistence unit
//                .build();
//    }
//	
//	@Primary
//	@Qualifier(value = "h2EntityManager")
//	@Bean(name = "h2EntityManager")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
//    }
//	
//	@Primary
//	@Qualifier(value = "h2TransactionManager")
//	@Bean(name = "h2TransactionManager")
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory); // 构建事务管理器
//    }
}
