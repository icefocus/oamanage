package com.hkj365.oamanage.config;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.hkj365.oamanage.controller")
public class WebConfig extends WebMvcConfigurerAdapter{
	final static Logger logger = LoggerFactory.getLogger(WebConfig.class);
	
	@Bean
	public ViewResolver viewResolver(){
		logger.info("config the viwResolver.");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		//  设定是否要在应用程序上下文作为请求属性可以通过懒惰检查，所有的Spring bean的属性得到一次访问。
		resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		//set .jsp including jstl
		return resolver;
	}
	
	//@bean等同于xml <bean>    
	//原xml注释<!-- 配置文件上传，如果没有使用文件上传可以不用配置，当然如果不配，那么配置文件中也不必引入上传组件包 -->
	@Bean 
	public MultipartResolver multipartResolver(){
		logger.info("config the commonMUltipartResolver");
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("utf-8");
		commonsMultipartResolver.setMaxUploadSize(1048576);
		commonsMultipartResolver.setMaxInMemorySize(40960);
		return commonsMultipartResolver;
	}
	
	
	//Asking dispatcherServlet to forward requests for static resources 
	// to the servlet container's default servlet and not to try to handle them itself. 
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		logger.info("config the configureDefaultServletHandling.");
		configurer.enable();
	}
	

    //Add handlers to serve static resources such as images, js, and, css files 
	// from specific locations under web application root, the classpath, and others.
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
	   logger.info("config addResourceHandlers");
     // TODO Auto-generated method stub
     super.addResourceHandlers(registry);
   }

}
