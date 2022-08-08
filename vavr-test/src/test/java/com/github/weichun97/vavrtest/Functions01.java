package com.github.weichun97.vavrtest;

import io.vavr.*;
import io.vavr.control.Option;
import org.junit.Test;
import static org.junit.Assert.*;

public class Functions01 {

    @Test
    public void test(){
        // lambda
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Integer apply = sum.apply(1, 2);
        assertEquals(Integer.valueOf(3), apply);

        // 匿名类
        Function2<Integer, Integer, Integer> sum2 = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };
        Integer apply2 = sum2.apply(1, 2);
        assertEquals(Integer.valueOf(3), apply2);

        // of
        Function2<Integer, Integer, Integer> sum3 =
                Function2.of(this::sum);
        Integer apply3 = sum3.apply(1, 2);
        assertEquals(Integer.valueOf(3), apply3);
    }

    /**
     * 链式调用函数
     * 正向：andThen
     * 反向: compose
     */
    @Test
    public void testComposition(){
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

        Function1<Integer, Integer> add1AndMultiplyBy2_1 = plusOne.andThen(multiplyByTwo);
        assertEquals(Integer.valueOf(6), add1AndMultiplyBy2_1.apply(2));

        Function1<Integer, Integer> add1AndMultiplyBy2_2 = multiplyByTwo.compose(plusOne);
        assertEquals(Integer.valueOf(6), add1AndMultiplyBy2_2.apply(2));
    }

    /**
     * 结果封装成 Option, 类似于 Optional
     */
    @Test
    public void testLifting(){
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);
        // = None
        Option<Integer> i1 = safeDivide.apply(1, 0);
        // = Some(2)
        Option<Integer> i2 = safeDivide.apply(4, 2);


        Function2<Integer, Integer, Option<Integer>> sum = Function2.lift(this::sum2);
        // = None
        Option<Integer> optionalResult = sum.apply(-1, 2);
    }

    /**
     * 按照顺序替换部分参数
     */
    @Test
    public void testPartialApplication(){
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        // 将 sum 的参数 a 固定为 2
        Function1<Integer, Integer> add2 = sum.apply(2);
        assertEquals(Integer.valueOf(6), add2.apply(4));

        Function5<Integer, Integer, Integer, Integer, Integer, Integer> sum5 = (a, b, c, d, e) -> a + b + c + d + e;
        Function2<Integer, Integer, Integer> add6 = sum5.apply(2, 3, 1);
        assertEquals(Integer.valueOf(13), add6.apply(4, 3));
    }

    /**
     * 按照顺序替换部分参数,将后续参数变成 Function
     */
    @Test
    public void testCurrying(){
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> add2 = sum.curried().apply(2);
        assertEquals(Integer.valueOf(6), add2.apply(4));

        Function3<Integer, Integer, Integer, Integer> sum3 = (a, b, c) -> a + b + c;
        Function1<Integer, Function1<Integer, Integer>> add3 = sum3.curried().apply(2);
        assertEquals(Integer.valueOf(9), add3.apply(4).apply(3));
    }

    @Test
    public void testMemoization(){
        Function0<Double> hashCache =
                Function0.of(Math::random).memoized();

        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();

        assert randomValue1 == randomValue2;
    }



    public Integer sum(int a, int b){
        return a + b;
    }

    int sum2(int first, int second) {
        if (first < 0 || second < 0) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }
        return first + second;
    }
}
