package com.self.ws;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfBeans {
	
	@Value("${endpoint.address}")
	private String SOAP_URL;
	
	@Value("${camel.api.path}")
	private String contextPath;
	
	@Value("${endpoint.url}")
	private String endPointUrl;
	
	
	@Bean(name = "cxfFileUploader")
	public CxfEndpoint buildCxfEndpoint() {
		CxfEndpoint cxf = new CxfEndpoint();
		cxf.setAddress(SOAP_URL);
		cxf.setMtomEnabled(true);
		Map<String, Object> cxfProperties = new HashMap<String,Object>();
		cxfProperties.put("publishedEndpointUrl",endPointUrl);
		cxf.setProperties(cxfProperties);
		return cxf;
	}
	
	@Bean
	ServletRegistrationBean servletRegistrationBean() {
	    ServletRegistrationBean servlet = new ServletRegistrationBean
	      (new CamelHttpTransportServlet(), contextPath+"/*");
	    servlet.setName("CamelServlet");
	    return servlet;
	}
}
