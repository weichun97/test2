package com.github.weichun97.vavrtest;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.Test;

import static org.junit.Assert.*;

public class Tuples {

    /**
     * 创建元祖
     */
    @Test
    public void testCreate(){
        // (Java, 8)
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);

        // "Java"
        String s = java8._1;
        // 8
        Integer i = java8._2;

        assertEquals("Java", s);
        assertEquals(Integer.valueOf(8), i);
    }

    /**
     * 通过 map 逐个修改旧元祖的元素生成新元祖
     */
    @Test
    public void testMap(){
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        // (vavr, 1)
        Tuple2<String, Integer> that = java8.map(
                s -> s.substring(2) + "vr",
                i -> i / 8
        );
        assertEquals("vavr", that._1);
        assertEquals(Integer.valueOf(1), that._2);
    }

    /**
     * 只使用一个函数修改元祖
     */
    @Test
    public void testOneMap(){
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        // (vavr, 1)
        Tuple2<String, Integer> that = java8.map(
                (s, i) -> Tuple.of(s.substring(2) + "vr", i / 8)
        );
        assertEquals("vavr", that._1);
        assertEquals(Integer.valueOf(1), that._2);
    }

    @Test
    public void testApply(){
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        String that = java8.apply(
                (s, i) -> s.substring(2) + "vr " + i / 8
        );
        assertEquals("vavr 1", that);
    }
}
