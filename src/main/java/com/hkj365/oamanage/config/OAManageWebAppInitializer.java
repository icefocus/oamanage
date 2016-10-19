package com.hkj365.oamanage.config;


import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class OAManageWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	final static Logger logger = LoggerFactory.getLogger(OAManageWebAppInitializer.class);
	@Override
	protected String[] getServletMappings() {
		//in this case, it's mapped to /, indicating that it will be the application's default servlet.
		return new String[] { "/" };
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		logger.info("config the getRootConfig");
		//RootConfig.class be used to configure the application context created by ContextLoaderListener.
		//contextLoadListener load other beans
		return new Class<?>[] { RootConfig.class };
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		logger.info("config the getConfigClasses");
		//asked that DispatcherServlet load its application context with beans defined in the WebConfig.
		//DispatcherServlet load beans containing web components such as controllers, view resolvers,
		//and handler mappings. 
		return new Class<?>[] { WebConfig.class };
	}

	
	/*
	By overriding customizeRegistration(), you can apply additional configuration to DispatcherServlet.
	For instance, If you plan to use Servlet 3.0 support
	for multipart configuration, you need to enable DispatcherServlet’s registration
	to enable multipart requests.
	*/
	@Override
	protected void customizeRegistration(Dynamic registration){
		registration.setMultipartConfig(new MultipartConfigElement("/Users/Randy/Downloads", 2097152, 4194304, 0));
		registration.setInitParameter("dispatchOptionsRequest", "true");
		registration.setInitParameter("defaultHtmlEscape", "true");  
        registration.setInitParameter("spring.profiles.active", "default");
	}
	
	@Override
	protected Filter[] getServletFilters(){
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		//DelegatingFilterProxy securityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");
		//return new Filter[] {characterEncodingFilter, securityFilterChain};
		return new Filter[] {characterEncodingFilter};
	}
	
}
