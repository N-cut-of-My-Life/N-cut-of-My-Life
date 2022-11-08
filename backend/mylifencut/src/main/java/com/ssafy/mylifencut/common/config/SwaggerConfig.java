package com.ssafy.mylifencut.common.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Server;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

	@Bean
	public Docket api() {
		Server serverLocal = new Server("local", "http://localhost:8080", "for local usages", Collections.emptyList(),
			Collections.emptyList());
		Server testServer = new Server("test", "https://k7b105.p.ssafy.io", "for testing", Collections.emptyList(),
			Collections.emptyList());
		return new Docket(DocumentationType.OAS_30)
			.servers(serverLocal, testServer)
			.groupName("api")
			.apiInfo(this.apiInfo())
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}

	private ApiInfo apiInfo() {
		String description = "인생N컷 API Docs";
		return new ApiInfoBuilder()
			.title("인생N컷 API")
			.description(description)
			.version("3.0")
			.build();
	}
}