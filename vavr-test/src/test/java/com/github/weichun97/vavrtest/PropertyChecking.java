package com.github.weichun97.vavrtest;

import io.vavr.CheckedFunction1;
import io.vavr.collection.Stream;
import io.vavr.test.Arbitrary;
import io.vavr.test.CheckResult;
import io.vavr.test.Property;
import org.junit.Test;

import java.util.function.Predicate;

import static io.vavr.API.*;

public class PropertyChecking {

    private static Predicate<Integer> divisibleByTwo = i -> i % 2 == 0;
    private static Predicate<Integer> divisibleByFive = i -> i % 5 == 0;


    @Test
    public void test(){
        Arbitrary<Integer> ints = Arbitrary.integer();
// square(int) >= 0: OK, passed 1000 tests.
        Property.def("square(int) >= 0")
                .forAll(ints)
                .suchThat(i -> i * i >= 0)
                .check()
                .assertIsSatisfied();


        Arbitrary<Integer> multiplesOf2 = Arbitrary.integer()
                .filter(i -> i > 0)
                .filter(i -> i % 2 == 0 && i % 5 != 0);
        CheckedFunction1<Integer, Boolean> mustEquals = i -> stringsSupplier().get(i).equals("DividedByTwoWithoutRemainder");
        CheckResult result = Property
                .def("Every second element must equal to DividedByTwoWithoutRemainder")
                .forAll(multiplesOf2)
                .suchThat(mustEquals)
                .check(10000, 100);
        result.assertIsSatisfied();

        Arbitrary<Integer> multiplesOf5 = Arbitrary.integer()
                .filter(i -> i > 0)
                .filter(i -> i % 5 == 0 && i % 2 == 0);
        CheckedFunction1<Integer, Boolean> mustEqualsOf5
                = i -> stringsSupplier().get(i).endsWith("DividedByTwoAndFiveWithoutRemainder");
        Property.def("Every fifth element must equal to DividedByTwoAndFiveWithoutRemainder")
                .forAll(multiplesOf5)
                .suchThat(mustEqualsOf5)
                .check(10_000, 1_000)
                .assertIsSatisfied();
    }

    private Stream<String> stringsSupplier() {
        return Stream.from(0).map(i -> Match(i).of(
                Case($(divisibleByFive.and(divisibleByTwo)), "DividedByTwoAndFiveWithoutRemainder"),
                Case($(divisibleByFive), "DividedByFiveWithoutRemainder"),
                Case($(divisibleByTwo), "DividedByTwoWithoutRemainder"),
                Case($(), "")));
    }

    @Test
    public void test2(){
        System.out.println(10_000);
    }
}
