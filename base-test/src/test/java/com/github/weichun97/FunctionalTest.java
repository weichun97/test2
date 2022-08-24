package com.github.weichun97;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import io.vavr.Predicates;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalTest {

    @Test
    public void testPredicate(){
        List<Integer> integers = io.vavr.collection.List.of(1, 255, 54, 2, 88).toJavaList();
        System.out.println(filter(integers, v -> v > 20));
    }

    @Test
    public void testConsumer(){
        Consumer<User> consumer = user -> user.setName("张三");
        Consumer<User> consumer2 = user2 -> System.out.println(user2.getName());
        Consumer<User> combineConsumer = consumer.andThen(consumer2);

        User user = new User();
        combineConsumer.accept(user);
    }

    @Test
    public void testSupplier(){
        Supplier<User> supplier = () -> new User(RandomUtil.randomString(3));
        User user = supplier.get();
    }

    @Test
    public void testFunction(){
        Function<User, String> function = User::getName;
        Assert.assertEquals("张三", function.apply(new User("张三")));
    }

    private <T> List<T> filter(List<T> list, Predicate<T> predicate){
        if(CollUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        List<T> res = new ArrayList<>();
        list.forEach(v -> {
            if(predicate.test(v)){
                res.add(v);
            }
        });
        return res;
    }

    public static class User{
        private String name;
        public User() {
        }

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
