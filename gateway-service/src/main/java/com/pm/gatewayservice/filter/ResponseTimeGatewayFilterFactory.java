package com.pm.gatewayservice.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Component
public class ResponseTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    public ResponseTimeGatewayFilterFactory(){
        super(Object.class);
    }


    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        exchange.getResponse().getHeaders()
                                .add("X-Response-Time", Instant.now().toString());
                    }));
        };
    }
}
