package br.com.utfpr.multitenancy;

import org.springframework.stereotype.Component;

@Component
public class TenancyContext {

	public static final String DEFAULT_TENANT_ID = "hsqlTenant";
	
	private ThreadLocal tenantId = ThreadLocal.withInitial(() -> DEFAULT_TENANT_ID);
	
	public void setTenantId(String tenantId) {
		this.tenantId.set(tenantId);
	}
	
	public String getTenantId() {
		return (String) this.tenantId.get();
	}
}
