package com.mx.proyecto.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration //Identifica que nuestra clase es de tipo configuración
@EnableWebMvc //Habilita la parte web MVC = Modelo Vista Controlador
@ComponentScan("com.mx.proyecto") //Sirve para indicarle a Spring que es sobre
//ese paquete va a buscar aquellas clases que nosotros hayamos afiliado/integrado a spring con las anotaciones

public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resol = new InternalResourceViewResolver ();
		resol.setPrefix("/WEB-INF/views/");
		resol.setSuffix(".jsp");
		return resol;
	}
	
	@Override 
	public void addResourceHandlers (ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/resources/**")
			.addResourceLocations("classpath:/resources/");
	}
	
}
