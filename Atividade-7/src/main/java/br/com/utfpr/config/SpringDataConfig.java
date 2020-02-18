package br.com.utfpr.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableJpaRepositories("br.com.utfpr.repository")
@EnableTransactionManagement
public class SpringDataConfig {

	@Bean
	public DataSource datasource() {
		
		BoneCPDataSource ds = new BoneCPDataSource();
		ds.setUser("root");
		ds.setPassword("root");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/curso-spring-db?useTimezone=true&serverTimezone=UTC");
		ds.setDriverClass("com.mysql.jdbc.Driver");
		
		return ds;
	}
	
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		
		LocalContainerEntityManagerFactoryBean factory = 
				new LocalContainerEntityManagerFactoryBean();
		
		HibernateJpaVendorAdapter vendorAdapter = 
				new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(false);
		vendorAdapter.setShowSql(true);
		
		factory.setDataSource(datasource());
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("br.com.utfpr.entity");
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
