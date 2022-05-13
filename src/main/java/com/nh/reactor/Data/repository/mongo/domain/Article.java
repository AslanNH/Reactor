package com.nh.reactor.Data.repository.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("article")
public class Article {

    @Id
    private String id;
    private String title;
    private String content;
    private String author;
}
