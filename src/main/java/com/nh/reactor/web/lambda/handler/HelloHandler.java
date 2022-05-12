package com.nh.reactor.web.lambda.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Component
public class HelloHandler {

    public Mono<ServerResponse> hello(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hello World lambda"));
    }

    @Configuration
    public class HelloRouter {

        @Bean
        public RouterFunction<ServerResponse> routeHello(HelloHandler
                                                                 helloHandler){
            return RouterFunctions.route(RequestPredicates.GET("/hello")
                            .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                    helloHandler::hello);
        }
    }
}
