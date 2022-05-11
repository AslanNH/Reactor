package com.nh.reactor.Chapter2.construct;

import reactor.core.publisher.Flux;

/**
 * 多个元素序列
 */
public class FluxConstructDemo {

    public static void main(String[] args) {
        Flux.just("Hello"," ","World").subscribe(System.out::print);
        System.out.println("--------------------");
        // 只能生成一个元素序列
        Flux.generate(sink->{
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::print);
        System.out.println("--------------------");
        // 可以生成多个元素序列
        Flux.create(sink->{
            for (int i=0;i<10;i++){
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::print);
    }
}
