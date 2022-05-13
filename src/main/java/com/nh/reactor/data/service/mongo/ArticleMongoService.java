package com.nh.reactor.data.service.mongo;

import com.nh.reactor.data.repository.mongo.dao.ArticleReactiveMongoRepository;
import com.nh.reactor.data.repository.mongo.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@Service
public class ArticleMongoService {

    @Autowired
    private  ArticleReactiveMongoRepository articleRepository;

    public Mono<Article> save(Article article){
        return articleRepository.save(article);
    }

    public Mono<Article> findOne(String id){
        return articleRepository.findById(id).log("findOneArticle");
    }

    public Flux<Article> findAll(){
        return articleRepository.findAll().log("findAll");
    }

    public Mono<Void> delete(String id){
        return articleRepository.deleteById(id).log("deleteOneArticle");
    }

    public Flux<Article> findByAuthor(String author){
        Article e = new Article();
        e.setAuthor(author);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withMatcher(author,startsWith())
                .withIncludeNullValues();

        Example<Article> example = Example.of(e,matcher);

        Flux<Article> articles = articleRepository.findAll(example).log("findByAuthor");

        return articles;

    }

}
