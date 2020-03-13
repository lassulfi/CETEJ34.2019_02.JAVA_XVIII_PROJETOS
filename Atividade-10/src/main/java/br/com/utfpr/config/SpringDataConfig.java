package br.com.utfpr.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("br.com.utfpr.repository")
@EnableTransactionManagement
public class SpringDataConfig {
	
	@Autowired
	private DataSource datasource;

	@Autowired
	private MultiTenancyDataSource multiTenancyDataSourceProvider;
	
	@Autowired
	private CurrentTenantIdentifierResolver currentTenantIdResolver;
	
//	@Bean
//	public DataSource datasource() {
//		
//		BoneCPDataSource ds = new BoneCPDataSource();
//		ds.setUser("root");
//		ds.setPassword("root");
//		ds.setJdbcUrl("jdbc:mysql://localhost:3306/curso-spring-db");
//		ds.setDriverClass("com.mysql.jdbc.Driver");
//		
//		return ds;
//	}
	
	private Map<String, Object> multiTenancyJpaProperties() {
		Map<String, Object> hibernateProps = new HashMap<>();
		hibernateProps.put(Environment.USE_NEW_ID_GENERATOR_MAPPINGS, "false");
		hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
		hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenancyDataSourceProvider);
		hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdResolver);
		
		return hibernateProps;
	}
	
	@Bean(value = "entityManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		
		LocalContainerEntityManagerFactoryBean factory = 
				new LocalContainerEntityManagerFactoryBean();
		
		HibernateJpaVendorAdapter vendorAdapter = 
				new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		
		factory.setDataSource(this.datasource);
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("br.com.utfpr.entity");
		factory.setJpaPropertyMap(multiTenancyJpaProperties());
		factory.afterPropertiesSet();
		
		return factory.getObject();
	}
	
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactory());
		manager.setJpaDialect(new HibernateJpaDialect());
		
		return manager;
	}
}
