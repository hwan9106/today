package kr.human.di.config;

import javax.sql.DataSource;

public interface DatabaseConfig {
	 
    DataSource createDataSource();
     
}
