package br.com.utfpr.multitenancy;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MultiTenancyDataSourceProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

	private DataSource mHsqlDataSource;
	private DataSource mH2DataSource;
	
	private Map<String, DataSource> mDataSourceMap;

	@Autowired
	public MultiTenancyDataSourceProvider(DataSource hsqlDataSource, DataSource h2DataSource) {
		this.mHsqlDataSource = hsqlDataSource;
		this.mH2DataSource = h2DataSource;
	}

	@PostConstruct
	public void load() {
		mDataSourceMap = new HashMap<>();
		mDataSourceMap.put("hsqlTenant", mHsqlDataSource);
		mDataSourceMap.put("h2Tenant", mH2DataSource);		
	}
	
	@Override
	protected DataSource selectAnyDataSource() {
		return mDataSourceMap.get(TenancyContext.DEFAULT_TENANT_ID);
	}

	@Override
	protected DataSource selectDataSource(String tenantId) {
		if(!tenantId.isEmpty()) {
			return mDataSourceMap.get(tenantId);
		}
		
		throw new HibernateException("Nenhum tenant selecionado");
	}
	
	
}
