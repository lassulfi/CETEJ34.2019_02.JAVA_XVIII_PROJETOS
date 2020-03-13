package br.com.utfpr.multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MultiTenancyCurrentIdResolver implements CurrentTenantIdentifierResolver {

	@Autowired	
	private TenancyContext tenancyContext;

	@Override
	public String resolveCurrentTenantIdentifier() {
		return tenancyContext.getTenantId();
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
	
	
}
