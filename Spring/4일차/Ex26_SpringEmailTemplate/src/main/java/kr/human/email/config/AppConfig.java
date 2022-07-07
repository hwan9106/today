package kr.human.email.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
@ComponentScan(basePackages="kr.human.email")
public class AppConfig {
	@Bean("mailSender")
	   public JavaMailSender getJavaMailSender() {
	      JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	      
	      // Using gmail
	        mailSender.setHost("smtp.naver.com");
	        mailSender.setPort(587);
	        mailSender.setUsername("hwan9532@naver.com"); // 이메일 계정
	        mailSender.setPassword("xoghks12");     // 이메일 비번
	         
	        Properties javaMailProperties = new Properties();
	        javaMailProperties.put("mail.smtp.starttls.enable", "true");
	        javaMailProperties.put("mail.smtp.auth", "true");
	        javaMailProperties.put("mail.transport.protocol", "smtp");
	        javaMailProperties.put("mail.debug", "true");//Prints out everything on screen
	        javaMailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
	        mailSender.setJavaMailProperties(javaMailProperties);
	        return mailSender;
	   }
	
	 /*
     * FreeMarker configuration.
     */
	@Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("/fmtemplates/");
        return bean;
    }
	/*
	 * Velocity configuration
	 */
    @Bean
    public VelocityEngine getVelocityEngine() throws VelocityException, IOException {
        Properties props = new Properties();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        VelocityEngine engine = new VelocityEngine(props);
        return engine;
    }
}
