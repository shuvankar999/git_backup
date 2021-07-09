package com.tip.inspection.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.TomcatDataSourceConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.metadata.TomcatDataSourcePoolMetadata;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ConfigurationProperties("spring.datasource_oracle")
public class dataSourceConfigForOracle extends TomcatDataSourceConfiguration {

	@Bean(name="datasourceCpAPIOracle")
	public DataSource dataSource() {
		return super.dataSource();
	}
	
	@Bean(name="jdbcCpAPIOracle")
	public JdbcTemplate jdbcTemplate(DataSource datasourceCpAPIOracle) {
		return new JdbcTemplate(datasourceCpAPIOracle);
	}
}
