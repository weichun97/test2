package com.github.weichun97.openfeigntest;

public class Test {

    public void test(Test test){
        System.out.println("testTest");
    }

    public void test(Object str){
        System.out.println("objTest");
    }

    public static void main(String[] args) {
        String s1 = "2121" ,m           ;
        String s2 = "2121";
        if(s1 == s2){
            System.out.println("s1 = hello");
        }else{
            System.out.println("s1 != hello");
        }
    }
}
