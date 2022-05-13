package com.nh.reactor;

import com.nh.reactor.Data.repository.mongo.domain.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@SpringBootApplication
public class ReactorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactorApplication.class, args);
    }

    @Value("${spring.data.redis.host}")
    private String redisHost;
    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(){
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
    }

    @Bean
    ReactiveRedisTemplate<String,String> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory
    ){
        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, RedisSerializationContext.string());
    }

    @Bean
    ReactiveRedisTemplate<String, Article> redisOperations(
            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory
    ){
        Jackson2JsonRedisSerializer<Article> serializer =
                new Jackson2JsonRedisSerializer<>(Article.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String,Article>builder
                = RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String,Article>context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory,context);
    }
}
