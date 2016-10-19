package com.hkj365.oamanage.config;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

 
/**
* @author Randy Wang
* 
*/
@Configuration
@ImportResource("classpath:log4j.xml")
public class LoggingConfig {
 
public LoggingConfig() {}
 /*
	@Bean
	public ConsoleAppender consoleAppender() {
		ConsoleAppender consoleAppender = new ConsoleAppender();
		consoleAppender.setThreshold(Level.ALL);
		PatternLayout patternLayout = new PatternLayout();
		patternLayout.setConversionPattern("%d %-5p [%c{1}] %m %n");
		consoleAppender.setLayout(patternLayout);
		return consoleAppender;
	}
	*/
	/*
	@Bean
	public FileAppender rollingAppender(){
		RollingFileAppender myFile = new RollingFileAppender();
		//myFile.setThreshold(Level.ALL);
		myFile.setFile("build.log");
		myFile.setMaxFileSize("10MB");
		myFile.setMaxBackupIndex(1);
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern("%d %-5p  [%c{1}] %m %n");
        myFile.setLayout(patternLayout);
        //myFile.activateOptions();
        return myFile;
		
	}
	*/
}
