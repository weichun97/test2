package com.github.weichun97;

import io.vavr.control.Either;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Test {



    private String name = "123";

    public void setName(String name){
        this.name = name;
    }

    public void out(){
        System.out.println(this.name);
    }

    public static class Test2 extends Test{
        private String name;
        public Test2(String name) {
            this.name = name;
        }
        public void setName(String name){
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2("456");
        test2.out();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
