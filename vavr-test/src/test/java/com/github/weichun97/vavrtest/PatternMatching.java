package com.github.weichun97.vavrtest;

import io.vavr.MatchError;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import io.vavr.match.annotation.Patterns;
import io.vavr.match.annotation.Unapply;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static io.vavr.API.*;
import static org.junit.Assert.*;
import static io.vavr.Predicates.*;

public class PatternMatching {

    @Test(expected = IllegalArgumentException.class)
    public void test(){
        int input = 2;
        String output = Match(input).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(3), "three"),
                Case($(), "?"));
        assertEquals("two", output);
    }

    @Test
    public void whenMatchesDefault_thenCorrect() {
        int input = 5;
        String output = Match(input).of(
                Case($(1), "one"),
                Case($(), "unknown"));

        assertEquals("unknown", output);
    }

    @Test(expected = MatchError.class)
    public void givenNoMatchAndNoDefault_whenThrows_thenCorrect() {
        int input = 5;
        Match(input).of(
                Case($(1), "one"),
                Case($(2), "two"));
    }

    @Test
    public void whenMatchWorksWithOption_thenCorrect() {
        int i = 10;
        Option<String> s = Match(i)
                .option(Case($(0), "zero"));

        assertTrue(s.isEmpty());
        assertEquals("None", s.toString());
    }

    @Test
    public void whenMatchWorksWithPredicate_thenCorrect() {
        int i = 3;
        String s = Match(i).of(
                Case($(is(1)), "one"),
                Case($(is(2)), "two"),
                Case($(is(3)), "three"),
                Case($(), "?"));

        assertEquals("three", s);
    }

    @Test
    public void givenInput_whenMatchesClass_thenCorrect() {
        Object obj=5;
        String s = Match(obj).of(
                Case($(instanceOf(String.class)), "string matched"),
                Case($(), "not string"));

        assertEquals("not string", s);
    }

    @Test
    public void givenInput_whenMatchesNull_thenCorrect() {
        Object obj=5;
        String s = Match(obj).of(
                Case($(isNull()), "no value"),
                Case($(isNotNull()), "value found"));

        assertEquals("value found", s);
    }

    @Test
    public void givenInput_whenContainsWorks_thenCorrect() {
        int i = 5;
        String s = Match(i).of(
                Case($(isIn(2, 4, 6, 8)), "Even Single Digit"),
                Case($(isIn(1, 3, 5, 7, 9)), "Odd Single Digit"),
                Case($(), "Out of range"));

        assertEquals("Odd Single Digit", s);
    }

    @Test
    public void givenInput_whenMatchAllWorks_thenCorrect() {
        Integer i = null;
        String s = Match(i).of(
                Case($(allOf(isNotNull(),isIn(1,2,3,null))), "Number found"),
                Case($(), "Not found"));

        assertEquals("Not found", s);
    }

    @Test
    public void givenInput_whenMatchesNoneOfWorks_thenCorrect() {
        Integer year = 1990;
        String s = Match(year).of(
                Case($(noneOf(isIn(1990, 1991, 1992), is(1986))), "Age match"),
                Case($(), "No age match"));

        assertEquals("No age match", s);
    }

    @Test
    public void whenMatchWorksWithCustomPredicate_thenCorrect() {
        int i = 3;
        String s = Match(i).of(
                Case($(n -> n == 1), "one"),
                Case($(n -> n == 2), "two"),
                Case($(n -> n == 3), "three"),
                Case($(), "?"));
        assertEquals("three", s);
    }

    @Test
    public void givenInput_whenContainsWorks_thenCorrect2() {
        int i = 5;
        BiFunction<Integer, List<Integer>, Boolean> contains
                = (t, u) -> u.contains(t);

        String s = Match(i).of(
                Case($(o -> contains
                        .apply(i, Arrays.asList(2, 4, 6, 8))), "Even Single Digit"),
                Case($(o -> contains
                        .apply(i, Arrays.asList(1, 3, 5, 7, 9))), "Odd Single Digit"),
                Case($(), "Out of range"));

        assertEquals("Odd Single Digit", s);
    }

    @Test
    public void givenObject_whenDecomposesJavaWay_thenCorrect() {
        Employee person = new Employee("Carl", "EMP01");

        String result = "not found";
        if (person != null && "Carl".equals(person.getName())) {
            String id = person.getId();
            result="Carl has employee id "+id;
        }

        assertEquals("Carl has employee id EMP01", result);
    }

    @Test
    public void whenMatchCreatesSideEffects_thenCorrect() {
        int i = 4;
        Match(i).of(
                Case($(isIn(2, 4, 6, 8)), o -> run(()->System.out.println("偶数"))),
                Case($(isIn(1, 3, 5, 7, 9)), o -> run(()->System.out.println("基数"))),
                Case($(), o -> run(() -> {
                    throw new IllegalArgumentException(String.valueOf(i));
                })));
    }

    public static class Employee {

        private String name;
        private String id;

        public Employee() {
        }

        public Employee(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
