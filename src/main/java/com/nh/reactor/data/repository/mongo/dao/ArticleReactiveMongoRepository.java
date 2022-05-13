package com.nh.reactor.data.repository.mongo.dao;

import com.nh.reactor.data.repository.mongo.domain.Article;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleReactiveMongoRepository
    extends ReactiveMongoRepository<Article,String>,
        ReactiveQueryByExampleExecutor<Article> {
}
