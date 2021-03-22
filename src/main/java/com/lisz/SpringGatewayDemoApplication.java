package com.lisz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringGatewayDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGatewayDemoApplication.class, args);
	}

	// routeLocator做的是服务端的跳转，浏览器的URL并不会改变. 根据某些计算做路由，计算逻辑可以写在这里
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(
					p -> p.path("/mongodb/person/**")
						  .filters(f -> f.stripPrefix(1))
						  .uri("http://192.168.1.102:8080")
				)
				.route(
					p -> p.path("/go/**")
					      .filters(f -> f.stripPrefix(1))
					      .uri("lb://MONGODB")
				)
				.route(
						p -> p.path("/abc/**")
						.filters(f -> f.stripPrefix(1))
						.uri("http://mashibing.com")
				)
			.build();
	}
}
