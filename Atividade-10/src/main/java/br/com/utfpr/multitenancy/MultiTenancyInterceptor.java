package br.com.utfpr.multitenancy;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class MultiTenancyInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private TenancyContext tenancyContext;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, Object> map = (Map<String, Object>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if(map.containsKey("tenantId")) {
			String id = (String) map.get("tenantId");
			request.getSession().setAttribute("tenantId", id);
			tenancyContext.setTenantId(id);
		}
		
		return true;
	}
	
	
}
