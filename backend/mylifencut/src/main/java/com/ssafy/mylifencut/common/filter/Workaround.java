package com.ssafy.mylifencut.common.filter;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.spi.DocumentationType;

@Component
public class Workaround implements WebMvcOpenApiTransformationFilter {

	@Override
	public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
		OpenAPI openApi = context.getSpecification();
		Server localServer = new Server();
		localServer.setDescription("local");
		localServer.setUrl("http://localhost:8080");

		Server testServer = new Server();
		testServer.setDescription("dev");
		testServer.setUrl("https://k7b105.p.ssafy.io");
		openApi.setServers(Arrays.asList(localServer, testServer));
		return openApi;
	}

	@Override
	public boolean supports(DocumentationType documentationType) {
		return documentationType.equals(DocumentationType.OAS_30);
	}
}
