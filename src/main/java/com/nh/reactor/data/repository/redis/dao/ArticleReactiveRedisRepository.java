package com.nh.reactor.data.repository.redis.dao;

import com.nh.reactor.data.repository.mongo.domain.Article;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ArticleReactiveRedisRepository {

    Mono<Boolean> saveArticle(Article article);

    Mono<Boolean> updateArticle(Article article);

    Mono<Boolean> deleteArticle(String articleId);

    Mono<Article> findArticleById(String articleId);

    Flux<Article> findAllArticles();
}
