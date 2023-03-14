package com.enway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FileMangementFilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileMangementFilesApplication.class, args);
	}
	
	@Bean
	public Docket api() {

		return new Docket(DocumentationType.OAS_30).select().apis( RequestHandlerSelectors.any())
				.paths( PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {

		return new ApiInfoBuilder().title("RestAPI").description("Employee Service Exposed Operations").version("1.0")
				.build();
	}

}
