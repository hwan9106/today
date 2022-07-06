package kr.human.di.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("kr.human.di") // kr.human.di 패키지에 있는 클래스들을 검색해서 자바빈을 자동으로 등록해라!!!
@PropertySource("classpath:jdbc.properties")
public class AppConfig {
	@Value("${m.driver}")
	private String driverClass;
	@Value("${m.url}")
	private String url;
	@Value("${m.username}")
	private String username;
	@Value("${m.password}")
	private String password;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
}