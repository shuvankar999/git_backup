package com.tip.inspection.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.TomcatDataSourceConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration 
@ConfigurationProperties("spring.datasource_sybase")
public class DataSourceConfigForSybase extends TomcatDataSourceConfiguration{

	@Bean(name="datasourceCpAPISybase")
	public DataSource dataSource() {
		return super.dataSource();
	}
	
	@Bean(name="jdbcTemplate")
	public JdbcTemplate jdbcTemplate(DataSource datasourceCpAPISybase) {
		return new JdbcTemplate(datasourceCpAPISybase);
	}
}
