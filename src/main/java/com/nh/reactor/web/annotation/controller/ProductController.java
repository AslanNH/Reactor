package com.nh.reactor.web.annotation.controller;

import com.nh.reactor.web.annotation.Domain.Product;
import com.nh.reactor.web.annotation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * WebFlux与SpringMVC的主要区别是底层通信方式
 * WebFlux：非阻塞的ServerHttpRequest和ServerHttpResponse对象
 * SpringMVC：阻塞的HttpServletRequest和HttpServletResponse
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public Flux<Product> getProducts(){
        return this.productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable("id") final String id){
        return this.productService.getProductById(id);
    }

    @PostMapping("")
    public Mono<Void> createProduct(@RequestBody final Mono<Product> product){
        return this.productService.createOrUpdateProduct(product);
    }

    @DeleteMapping("/{id}")
    public Mono<Product> delete(@PathVariable("id") final String id){
        return this.productService.deleteProduct(id);
    }
}
