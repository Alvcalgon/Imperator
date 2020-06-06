package com.fone.api.FOne.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.fone.api.FOne.controllers"))
				.paths(PathSelectors.any())
				.build()
				.tags(new Tag("Circuito", "Controlador de circuito"),
					  new Tag("Escudería", "Controlador de escudería"),
					  new Tag("Clasificación general de escuderías",
							  "Controlador de clasificación general de escudería"),
					  new Tag("Piloto", "Controlador de piloto"),
					  new Tag("Clasificación general de pilotos",
							  "Controlador de clasificación general de pilotos"),
					  new Tag("Carrera", "Controlador de carrera"),
					  new Tag("Resultado", "Controlador de resultado"))
				.apiInfo(getApiInfo())
				.host("fone-api.herokuapp.com");
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo("API REST - FOne",
				           "Se trata de una api que permite realizar consultas de datos de la Fórmula 1 desde 1950 hasta 2019", "1.0.0",
				           "La API ha sido desarrollada por fines meramente académicos",
				           new Contact("alvcalgon", "", "alvcalgon@alum.us.es"),
				           "API desarrollada por alvcalgon como parte del TFG",
				           "",
				           Collections.emptyList());
	}
	
}
