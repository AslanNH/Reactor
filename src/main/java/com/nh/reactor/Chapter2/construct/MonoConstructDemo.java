package com.nh.reactor.Chapter2.construct;

import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * 单个元素序列
 */
public class MonoConstructDemo {

    public static void main(String[] args) {
        // Optional不为空才会给Mono赋值
        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
        System.out.println("===========================");
        Mono.create(sink->
                sink.success("World")).subscribe(System.out::println);

    }
}
