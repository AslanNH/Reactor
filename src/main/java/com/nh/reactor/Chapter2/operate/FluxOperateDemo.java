package com.nh.reactor.Chapter2.operate;

import com.sun.javafx.binding.StringFormatter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Flux操作符
 */
public class FluxOperateDemo {

    public static void main(String[] args) {
        condition();
    }

    /**
     * 条件操作符
     */
    static void condition(){
        // defaultIfEmpty

    }
    /**
     * 组合操作符
     */
    static void combine(){
       // startWith:在序列开始插入指定元素
        Flux.range(3,18).startWith(1,2).subscribe(System.out::print);
        System.out.println("---------------------");
        // merge 合并多个序列
        Flux.merge(Flux.range(1,5),Flux.range(6,5)).toStream().forEach(System.out::print);
        System.out.println("---------------------");
        // zipWith 将两个序列中元素，一对一合并为一个集合  [a,c][b,d]
        Flux.just("a","b").zipWith(Flux.just("c","d")).subscribe(System.out::print);
        Flux.just("a","b").zipWith(Flux.just("c","d"),(s1,s2)-> String.format("%s+%s",s1,s2)).subscribe(System.out::println);
    }
    /**
     * 过滤操作符
     */
    static void filter(){
        //filter:过滤流，只留下满足条件的元素
        Flux.range(1,10).filter(i->i%2==0).subscribe(System.out::print);
        System.out.println("---------------------");
        // last:返回流最后一个元素
        Flux.range(1,10).last().subscribe(System.out::print);
        System.out.println("---------------------");
        // skip/skipLast:忽略数据流的前/后n个元素
        Flux.range(1,10).skip(3).subscribe(System.out::print);
        // take/takeLast:从流中获取前/后n个数据
        System.out.println("---------------------");
        Flux.range(1,10).take(5).subscribe(System.out::print);

    }
    /**
     * 转换操作符
     */
    static void transfer(){
        // buffer：把当前流中的元素收集到集合中，并把集合对象作为流中的新元素。
        // 把1~50个元素每10个收集到一个集合，然后再放到流中
        Flux.range(1,50).buffer(10).subscribe(System.out::println);
        // map:对流中的每个元素应用一个映射函数
        Flux.range(1,50).map(a->{
            a = a*10;
            return a;
        }).buffer(10).subscribe(System.out::println);
        // flagMap:把流中的每个元素转换成一个流，再把转换之后得到的所有六中的元素进行合并
        Flux.range(1,50).flatMap(x-> Mono.just(x*10))
                .buffer(10).subscribe(System.out::println);
        // window:将当前流中的数据收集到另一个Flux序列中，返回Flux<Flux<T>>
        Flux.range(1,5).window(2).toIterable().forEach(w->{
            w.subscribe(System.out::println);
            System.out.println("---------------------");
        });
    }

}
