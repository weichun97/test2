package com.github.weichun97.generatetest;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class Test {

    public static void main(String[] args) {
        List<Detail> details = new ArrayList<>();
        details.add(new Detail("123", CollUtil.newArrayList("test1", "test2")));
        details.add(new Detail("2332", CollUtil.newArrayList("test3", "test4")));

        List<String> collect = details.parallelStream().map(Detail::getName).collect(Collectors.toList());
        System.out.println(collect);

        List<String> collect2 = details.parallelStream()
                .flatMap(detail -> detail.getParts().stream())
                .collect(Collectors.toList());
        System.out.println(collect2);


    }

    @org.junit.jupiter.api.Test
    public void testReduce(){
        List<Integer> integers = Arrays.asList(1, 1, 1);
        Integer reduced = integers.stream().reduce(23, (a, b) -> a - b);
        Assert.assertEquals(Integer.valueOf(20), reduced);

        Integer combiner_was_called = Arrays.asList(1, 2, 3).parallelStream()
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    log.info("combiner was called");
                    return a + b;
                });
        System.out.println(combiner_was_called);
    }

    @org.junit.Test
    public void testCreate(){
        Arrays.stream(new Integer[]{1, 2, 3});
        Stream.<String>builder().add("123").add("4556").build().forEach(System.out::println);

        Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);

        Stream.iterate(40, n -> n + 2).limit(10).forEach(System.out::println);

        // range
        IntStream.range(1, 3).forEach(System.out::println);

        // rangeClosed
        IntStream.rangeClosed(1, 3).forEach(System.out::println);

        // random 方法生成 IntStream 流
        new Random().ints(5).forEach(System.out::println);

        "abc".chars().forEach(System.out::println);

        Pattern.compile(", ").splitAsStream("a, b, c").forEach(System.out::println);

        Stream.of("abcd", "bbcd", "cbcd").skip(1).forEach(System.out::println);
    }

    @org.junit.Test
    public void test(){
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        int counter = 0;
        list.stream().filter(element -> {
            log.info("filter() was called");
            return element.contains("2");
        }).map(element -> {
            log.info("map() was called");
            return element.toUpperCase();
        });
    }

    @org.junit.Test
    public void testCollector(){
        List<Product> productList = Arrays.asList(
                new Product(23, "potatoes"),
                new Product(14, "orange"),
                new Product(13, "lemon"),
                new Product(23, "bread"),
                new Product(13, "sugar"));
        String toString = productList.stream().map(Product::getName)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(toString);

        Double avgPrice = productList.stream()
                .collect(Collectors.averagingInt(Product::getPrice));
        System.out.println(avgPrice);

        Integer sum = productList.stream()
                .collect(Collectors.summingInt(Product::getPrice));
        System.out.println(sum);


        Integer max = productList.stream()
                .collect(Collectors.summarizingInt(Product::getPrice)).getMax();
        System.out.println(max);
    }

    @org.junit.Test
    public void testStatistical(){
        List<Product> productList = Arrays.asList(
                new Product(23, "potatoes"),
                new Product(14, "orange"),
                new Product(13, "lemon"),
                new Product(23, "bread"),
                new Product(13, "sugar"));

        IntSummaryStatistics collect = productList.parallelStream().collect(Collectors.summarizingInt(Product::getPrice));
        System.out.println(collect.getMax());
        System.out.println(collect.getMin());
;    }

    @Data
    @AllArgsConstructor
    static class Detail{
        private String name;
        private List<String> parts;
    }

    @Data
    @AllArgsConstructor
    static class Product {
        private Integer price;
        private String name;
    }
}
