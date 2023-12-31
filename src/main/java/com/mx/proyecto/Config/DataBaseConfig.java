package com.mx.proyecto.Config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource(value = {"classpath:/properties/CONEXIONES.properties"})

public class DataBaseConfig {
	
	@Autowired
	private Environment environment;
	
	@Bean(name = "dataSource") //NUESTRA CONEXION A LA BASE DE DATOS
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("BD_ORCL_DRIVER"));
		dataSource.setUrl(environment.getProperty("BD_LOCAL_ORCL_URL"));
		dataSource.setUsername(environment.getProperty("BD_LOCAL_ORCL_USERNAME"));
		dataSource.setPassword(environment.getProperty("BD_LOCAL_ORCL_PASSWORD"));
		return dataSource;	
	}
	
	/*JDBC TEMPLATE ES LA HERRAMIENTA QUE NOS PERMITIRÁ HACER CONSULTAS A LA BASE DE DATOS
	  INDICANDOLE HACIA QUE BASE DE DATOS VA A CONSULTAR*/
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}

}
