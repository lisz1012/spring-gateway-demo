package com.lisz.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class MyFilter2 implements Ordered, GlobalFilter {
	// 做各种限流、鉴权、校验，对于Cookie、tokens等。所有的Filter都可以以组件的形式嵌入到网关的接入层里
	// 跟JWT、Spring Security整合做鉴权，都是从exchange中拿到request，然后再拿到Headers、Cookie
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.out.println("222");
		return chain.filter(exchange); //继续FilterChain，类似于我们熟悉的J2EE里面的Filter的做法
	}

	@Override
	public int getOrder() {
		// 数字越小越先进入这个Filter，0表示不设置优先级，所有的Filter是同一优先级
		return 100; //低优先级
	}
}
