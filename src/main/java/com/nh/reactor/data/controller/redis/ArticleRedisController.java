package com.nh.reactor.data.controller.redis;

import com.nh.reactor.data.repository.mongo.domain.Article;
import com.nh.reactor.data.service.redis.ArticleRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/redis")
public class ArticleRedisController {

    @Autowired
    private ArticleRedisService articleService;
    @PostMapping("")
    public Mono<Boolean> createArticle(@RequestBody final Article article){
        return this.articleService.save(article);
    }

    @PutMapping("")
    public Mono<Boolean> updateArticle(@RequestBody final Article article){
        return this.articleService.update(article);
    }

    @GetMapping("")
    public Flux<Article> getArticles(){
        return this.articleService.findAllArticles();
    }

    @GetMapping("/{id}")
    public Mono<Article> getArticleById(@PathVariable("id") final String id){
        return this.articleService.findArticleById(id);
    }



    @DeleteMapping("/{id}")
    public Mono<Boolean> delete(@PathVariable("id") final String id){
        return this.articleService.delete(id);
    }

}
