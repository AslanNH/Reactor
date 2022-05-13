package com.nh.reactor.Data.controller.mongo;

import com.nh.reactor.Data.repository.mongo.domain.Article;
import com.nh.reactor.Data.service.mongo.ArticleMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/mongo")
public class ArticleMongoController {

    @Autowired
    private ArticleMongoService articleService;

    @GetMapping("")
    public Flux<Article> getArticles(){
        return this.articleService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Article> getArticleById(@PathVariable("id") final String id){
        return this.articleService.findOne(id);
    }

    @PostMapping("")
    public Mono<Article> createArticle(@RequestBody final Article article){
        return this.articleService.save(article);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") final String id){
        return this.articleService.delete(id);
    }

    @GetMapping("/author/{author}")
    public Flux<Article> getByAuthor(@PathVariable("author") final String author){
        return this.articleService.findByAuthor(author);
    }
}
