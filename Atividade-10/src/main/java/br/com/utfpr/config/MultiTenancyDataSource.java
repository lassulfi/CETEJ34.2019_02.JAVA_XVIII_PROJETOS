package br.com.utfpr.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.jolbox.bonecp.BoneCPDataSource;

@Component
public class MultiTenancyDataSource {

	@Bean(name = {"datasource", "hsqlDataSource" })
	public DataSource hsqlDataSource() {
		BoneCPDataSource ds = new BoneCPDataSource();
		ds.setDriverClass("org.hsqldb.jdbcDriver");
		ds.setJdbcUrl("jdbc:hsqldb:mem:hsqlTenant");
		ds.setUsername("sa");
		ds.setPassword("");
		
		return ds;
	}
	
	@Bean(name = "h2DataSource")
	public DataSource h2DataSource() {
		BoneCPDataSource ds = new BoneCPDataSource();
		ds.setDriverClass("org.h2.Driver");
		ds.setJdbcUrl("jdbc:h2:mem:h2Tenant");
		ds.setUsername("sa");
		ds.setPassword("");
		
		return ds;
	}
}
