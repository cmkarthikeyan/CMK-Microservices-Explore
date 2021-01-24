package com.cmk.microservices.apigateway.config;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
	
	/*@Bean
	public RouteLocator localHostRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/api/v1/employees*", "/api/v1/employees/*")
						.filters(f -> f.addResponseHeader("Hello", "CMK")).uri("lb://employee-service"))
				
				.route(r -> r.weight("group1", 6).uri("/api/v1/employees/*"))
				
				.route(p -> p.path("/dummy")
						.filters(f -> f.circuitBreaker(
								config -> config.setName("employee-cb").setFallbackUri("forward:/fallback")))
						.uri("lb://employee-service"))
				.build();
	}*/

}
