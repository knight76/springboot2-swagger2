package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.and;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("API")
				.select()
				.apis(and(RequestHandlerSelectors.basePackage("com.example.controller")))
				//.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
		        .build()
				.apiInfo(apiEndPointsInfo());
	}

	public ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Spring Boot REST API with Swagger2")
		                           .description("Language Management REST API")
		                           .contact(new Contact("knight76", "http://knight76.tistory.com", "knight76@gmail.com"))
		                           .license("Apache 2.0")
		                           .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
		                           .version("0.0.1-SNAPSHOT")
		                           .build();
	}
}
