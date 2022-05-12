package com.nh.reactor.web.lambda.handler;

import com.nh.reactor.web.annotation.Domain.Product;
import com.nh.reactor.web.annotation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

    @Autowired
    private ProductService productService;

    public Mono<ServerResponse> getProducts(ServerRequest request){
        return ServerResponse.ok().body(this.productService.getProducts(), Product.class);
    }

    public Mono<ServerResponse> getProductById(ServerRequest request){
        String id = request.pathVariable("id");

        return ServerResponse.ok()
                .body(this.productService.getProductById(id),Product.class);
    }

    public Mono<ServerResponse> createProduct(ServerRequest request){
        Mono<Product> product = request.bodyToMono(Product.class);
        return ServerResponse.ok().body(this.productService.createOrUpdateProduct(product),Product.class);
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest request){
        String id = request.pathVariable("id");
        return ServerResponse.ok().body(this.productService.deleteProduct(id),Product.class);
    }

    @Configuration
    public class ProductRouter{

        @Bean
        public RouterFunction<ServerResponse> routeProduct(ProductHandler productHandler){
            return RouterFunctions
                    .route(RequestPredicates.GET("/lambda").and(RequestPredicates
                                    .accept(MediaType.APPLICATION_JSON)),
                                productHandler::getProducts)
                    .andRoute(RequestPredicates.GET("/lambda/{id}").and(RequestPredicates
                                    .accept(MediaType.APPLICATION_JSON)),
                            productHandler::getProductById)
                    .andRoute(RequestPredicates.POST("/lambda/").and(RequestPredicates
                                    .accept(MediaType.APPLICATION_JSON)),
                            productHandler::createProduct)
                    .andRoute(RequestPredicates.DELETE("/lambda/{id}").and(RequestPredicates
                                    .accept(MediaType.APPLICATION_JSON)),
                            productHandler::deleteProduct);

        }
    }
}
