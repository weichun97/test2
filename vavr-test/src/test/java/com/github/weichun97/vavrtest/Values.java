package com.github.weichun97.vavrtest;

import io.vavr.Lazy;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static io.vavr.API.*;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

public class Values {

    @Test
    public void testOption(){
        Optional<String> maybeFoo = Optional.of("foo");
        assertEquals("foo", maybeFoo.get());
        Optional<String> maybeFooBar = maybeFoo.map(s -> (String)null)
                .map(s -> s.toUpperCase() + "bar");
        assertFalse(maybeFooBar.isPresent());

        Option<String> maybeFoo2 = Option.of("foo");
        assertEquals("foo", maybeFoo2.get());
        try {
            maybeFoo2.map(s -> (String)null)
                    .map(s -> s.toUpperCase() + "bar");
            Assert.fail();
        } catch (NullPointerException e) {
            // this is clearly not the correct approach
            System.out.println("空指针");
        }

        Option<String> maybeFoo3 = Option.of("foo");
        assertEquals("foo", maybeFoo3.get());
        Option<String> maybeFooBar3 = maybeFoo3.map(s -> (String)null)
                .flatMap(s -> Option.of(s).map(t -> t.toUpperCase() + "bar"));
        assertTrue(maybeFooBar3.isEmpty());

        Option<String> maybeFoo4 = Option.of("foo");
        assertEquals("foo", maybeFoo4.get());
        Option<String> maybeFooBar4 = maybeFoo4.flatMap(s -> Option.of((String)null))
                .map(s -> s.toUpperCase() + "bar");
        assertTrue(maybeFooBar4.isEmpty());
    }

    @Test
    public void testTry(){
        String str = Try.of(() -> bunchOfWork()).getOrElse("报错了");
        assertEquals("报错了", str);

        String str2 = Try.of(() -> bunchOfWork())
                .recover(NullPointerException.class, t -> nullExceptionHandler(t))
                .recover(RuntimeException.class, t -> "运行时异常")
                .getOrElse("报错了");
        assertEquals("运行时异常", str2);
    }

    @Test
    public void testLazy(){
        Lazy<Double> lazy = Lazy.of(Math::random);
        assertFalse(lazy.isEvaluated()); // = false
        Double aDouble = lazy.get();
        assertTrue(lazy.isEvaluated()); // = true
        assertEquals(aDouble, lazy.get());

        CharSequence chars = Lazy.val(() -> "Yay!", CharSequence.class);
        assertEquals("Yay!", chars.toString());
    }

    @Test
    public void testEither(){
        Integer str = computeWithEither(9).getOrElse(() -> null);
        String s = computeWithEither(9).swap().getOrElse("");
        assertNull(str);
        assertEquals("Marks not acceptable", s);
        assertEquals("Marks not acceptable!!!", computeWithEither(9).swap().map(v -> v + "!!!").get());

        assertFalse(computeWithEither(9).contains(90));
        assertTrue(computeWithEither(90).contains(90));
    }

    /**
     * 大于 85 分返回成功，否则返回错误信息
     *
     * @param marks
     * @return
     */
    public Either<String, Integer> computeWithEither(Integer marks){
        if (marks < 85) {
            return Either.left("Marks not acceptable");
        } else {
            return Either.right(marks);
        }
    }

    public String bunchOfWork(){
        throw new RuntimeException();
    }

    public String nullExceptionHandler(NullPointerException objectMatch){
        return "空指针异常";
    }
}
