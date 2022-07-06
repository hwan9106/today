package kr.human.di.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Profile("Development")
@Configuration
@PropertySource(value = {"classpath:properties/application.properties","classpath:properties/jdbc.properties"})
public class DevDatabaseConfig implements DatabaseConfig {
	
	@Value("${m.driver}")
	private String driverClassName;
	@Value("${m.url}")
	private String url;
	@Value("${m.username}")
	private String username;
	@Value("${m.password}")
	private String password;
	
    @Override
    @Bean
    public DataSource createDataSource() {
        System.out.println("Creating DEV database");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
 
}