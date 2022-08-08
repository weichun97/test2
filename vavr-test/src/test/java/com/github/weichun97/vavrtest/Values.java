package com.github.weichun97.vavrtest;

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

    public String bunchOfWork(){
        throw new RuntimeException();
    }

    public String nullExceptionHandler(NullPointerException objectMatch){
        return "空指针异常";
    }
}
