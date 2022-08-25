package com.github.weichun97;

import io.vavr.control.Either;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Test {



    @org.junit.Test
    public void test(){
        Either<Integer, String> either = Either.left(1);
        if(either.isLeft()){
            Integer integer = either.swap().get();
            System.out.println(integer);
        }
    }

    private String run1(String a){
        return a + "-后缀";
    }

    private void run2(String a){
        System.out.println(123);
    }
}
