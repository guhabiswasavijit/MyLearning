package com.self.ws;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

@Configuration
public class CxfBeans {
	
	@Value("${endpoint.address}")
	private String SOAP_URL;
	
	@Value("${camel.api.path}")
	private String contextPath;
	
	@Value("${endpoint.url}")
	private String endPointUrl;
	
	@Bean
	public SpringBus cxf() {        
	    return new SpringBus();
	}
	
	
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
	      (new CXFServlet(), contextPath+"/*");
	    servlet.setName("dispatcher");
	    return servlet;
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver loadMultipartResolver() { 
		final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(-1);
        return commonsMultipartResolver;
	}
	
	@Bean(name = "multipartFilterRegistrationBean")
    public FilterRegistrationBean multipartFilterRegistrationBean() {
        final MultipartFilter multipartFilter = new MultipartFilter();
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(multipartFilter);
        filterRegistrationBean.addInitParameter("multipartResolverBeanName", "multipartResolver");
        return filterRegistrationBean;
    }
}
