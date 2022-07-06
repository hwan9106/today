package kr.human.di.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Profile("Production")
@Configuration
@PropertySource(value = {"classpath:properties/application.properties","classpath:properties/jdbc.properties"})
public class ProductionDatabaseConfig implements DatabaseConfig {
	@Value("${o.driver}")
	private String driverClassName;
	@Value("${o.url}")
	private String url;
	@Value("${o.username}")
	private String username;
	@Value("${o.password}")
	private String password;
	
    @Override
    @Bean
    public DataSource createDataSource() {
        System.out.println("Creating DEV Production");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
 
}
