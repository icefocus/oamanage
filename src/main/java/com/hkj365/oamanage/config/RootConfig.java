package com.hkj365.oamanage.config;



import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages="com.hkj365.oamanage")
@PropertySource("classpath:jdbc.properties")
@MapperScan("com.hkj365.oamanage.dao")
@EnableTransactionManagement	//Enables Spring's annotation-driven transaction management capability, 
								//similar to the support found in Spring's <tx:*> XML namespace. 
@Import(LoggingConfig.class)
public class RootConfig {
	final static Logger logger = LoggerFactory.getLogger(RootConfig.class);
	@Value( "${driver}" )
	private String driver;
	
	@Value( "${url}" )
	private String url;
	
	@Value( "${username}")
	private String username;
	
	@Value("${password}")
	private String password;
	
	//初始化连接大小
	@Value("${initialSize}")
	private int initialSize;
	
	//连接池最大数量
	@Value("${maxActive}")
	private int maxActive;
	
	//连接池最大空闲
	@Value("${maxIdle}")
	private int maxIdle;
	
	//连接池最小空闲
	@Value("${minIdle}")
	private int minIdle;
	
	//连接最大等待时间
	@Value("${maxWait}")
	private long maxWait;
	
	//@Value("${eny}")
	//private String eny;
	
	private String mapperPath = "classpath:mapping/*.xml";
	
	
	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
		logger.info("config the placeholderConfigurer.");
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	//mybatis config
	@Bean(destroyMethod="close")
	public BasicDataSource dataSource(){
		logger.info("config the dataSource");
		BasicDataSource ds= new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setInitialSize(initialSize);
		ds.setMaxActive(maxActive);
		ds.setMaxIdle(maxIdle);
		ds.setMinIdle(minIdle);
		ds.setMaxWait(maxWait);
		logger.info("leaving the dataSource config." );
		return ds;	
	}

	//mybatis-spring  config
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception{
		logger.info("config the sqlSessionFactory");
		SqlSessionFactoryBean sqlSF = new SqlSessionFactoryBean();
		sqlSF.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSF.setMapperLocations(resolver.getResources(mapperPath));
		return sqlSF.getObject();
	}
	
	//transactionManager config
	@Bean
	public PlatformTransactionManager txManager(){
		logger.info("config the platformTransactionManager");
		return new DataSourceTransactionManager(dataSource());
	}
	

}
