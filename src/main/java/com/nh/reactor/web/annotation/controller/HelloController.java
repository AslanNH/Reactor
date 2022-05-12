package com.nh.reactor.web.annotation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @GetMapping("/")
    public Mono<String> index(){
        return Mono.just("Hello Spring Boot!");
    }

}
