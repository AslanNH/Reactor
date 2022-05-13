package com.nh.reactor.data.service.redis;

import com.nh.reactor.data.repository.mongo.domain.Article;
import com.nh.reactor.data.repository.redis.dao.ArticleReactiveRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ArticleRedisService {

    @Autowired
    private ArticleReactiveRedisRepository articleRepository;

    public Mono<Boolean> save(Article article){
        return articleRepository.saveArticle(article);
    }

    public Mono<Boolean> delete(String id){
        return articleRepository.deleteArticle(id);
    }

    public Mono<Article> findArticleById(String id){
        return articleRepository.findArticleById(id).log("findOneArticle");
    }

    public Flux<Article> findAllArticles(){
        return articleRepository.findAllArticles().log("findAllArticles");
    }

    public Mono<Boolean> update(Article article){
        return articleRepository.updateArticle(article);
    }
}
