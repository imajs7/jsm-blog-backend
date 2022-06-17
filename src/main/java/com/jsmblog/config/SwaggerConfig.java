package com.jsmblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
	
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(
					new Info()
							.title("JSM Blog Backend APIs")
							.description("Spring boot powered blogging application")
							.version("v0.0.1")
							.title("JSM Blog Core")
							.license(
									new License()
												.name("JSMedia7 1.0")
												.url("https://jsmedia7.in")
							)
				 )
				.externalDocs(
						new ExternalDocumentation()
								.description("SpringBoot API Documentation")
								.url("https://github.com/imajs7")
				);
	}
	
	public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("api", HandlerTypePredicate.forAnnotation(RestController.class));
    }

}
