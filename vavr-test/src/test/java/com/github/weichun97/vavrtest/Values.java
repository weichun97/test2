package com.github.weichun97.vavrtest;

import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import one.util.streamex.StreamEx;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import static io.vavr.API.*;
import static java.util.concurrent.Executors.newSingleThreadExecutor;
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

    @Test
    public void testFuture() throws ExecutionException, InterruptedException {
        Future<String> future = Future.of(()->"123213");
        assertEquals("123213", future.getOrElse("错误"));

        Future<String> resultFuture = Future.of(()->"123213");
        resultFuture.await();
        Option<Try<String>> futureOption = resultFuture.getValue();
        Try<String> futureTry = futureOption.get();
        String result = futureTry.get();
        assertEquals("123213", result);

        String result2 = Future.of(newSingleThreadExecutor(), () -> "HELLO")
                .getOrElse("error");
        assertEquals("HELLO", result2);


        Future<String> resultFuture2 = Future.of(() -> "12333")
                .onSuccess(v -> System.out.println("Successfully Completed - Result: " + v))
                .onFailure(v -> System.out.println("Failed - Result: " + v));

        Future<String> resultFuture3 = Future.of(() -> "12333")
                .onComplete(System.out::println)
                .andThen(finalResult -> System.out.println("Completed - 1: " + finalResult))
                .andThen(finalResult -> System.out.println("Completed - 2: " + finalResult.getOrElse("无结果")));


        Future<Integer> resultFuture4 = Future.of(() -> 10 / 0)
                .await()
                .onFailure(exception -> System.out.println(exception.getMessage()))
                ;
        assertEquals("/ by zero", resultFuture4.getCause().get().getMessage());

        Future<Integer> resultFuture5 = Future.of(() -> 10 / 0);
        assertThrows(ArithmeticException.class, resultFuture5::get);

        Future<Integer> resultFuture6 = Future.of(() -> 10 / 0)
                .await();
        assertTrue(resultFuture6.isCompleted());
        assertTrue(resultFuture6.isFailure());
        assertFalse(resultFuture6.isSuccess());

        Future<String> futureResult7 = Future.of(() -> "from Baeldung")
                .map(a -> "Hello " + a)
                .await();
        assertEquals("Hello from Baeldung", futureResult7.get());

        Future<Object> futureMap = Future.of(() -> 1)
                .flatMap((i) -> Future.of(() -> "Hello: " + i));
        assertEquals("Hello: 1", futureMap.get());

        Future<Object> future2 = Future.of(() -> 5)
                .transformValue(result3 -> Try.of(() -> "HELLO" + result3.get()));
        assertEquals("HELLO" + 5, future2.get());

        Future<String> f1 = Future.of(() -> "hello1");
        Future<String> f2 = Future.of(() -> "hello2");
        assertEquals(Tuple.of("hello1", "hello2"), f1.zip(f2).get());

        CompletableFuture<String> convertedFuture = Future.of(() -> "HELLO")
                .toCompletableFuture();
        assertEquals("HELLO", convertedFuture.get());

        Future<String> future3 = Future.of(() -> "Hello".substring(-1))
                .recover(x -> "fallback value");
        assertEquals("fallback value", future3.get());

        Future<String> future4 = Future.of(() -> "Hello".substring(-1))
                .recoverWith(x -> Future.of(() -> "fallback value"));
        assertEquals("fallback value", future4.get());

        Future<String> f11 = Future.of(() -> "Hello".substring(-1));
        Future<String> f22 = Future.of(() -> "Hello".substring(-2));
        Future<String> errorMessageFuture = f11.fallbackTo(f22);
        Future<Throwable> errorMessage = errorMessageFuture.failed();
        assertEquals("String index out of range: -1", errorMessage.get().getMessage());
    }

    @Test
    public void testValidation(){
        PersonValidator personValidator = new PersonValidator();
        // Valid(Person(John Doe, 30))
        Validation<Seq<String>, Person> valid = personValidator.validatePerson("John Doe", 30);
        assertTrue(valid.isValid());
        // Invalid(List(Name contains invalid characters: '!4?', Age must be greater than 0))
        Validation<Seq<String>, Person> invalid = personValidator.validatePerson("John? Doe!4", -1);
        assertFalse(invalid.isValid());
    }

    class PersonValidator {

        private static final String VALID_NAME_CHARS = "[a-zA-Z ]";
        private static final int MIN_AGE = 0;

        public Validation<Seq<String>, Person> validatePerson(String name, int age) {
            return Validation.combine(validateName(name), validateAge(age)).ap(Person::new);
        }

        private Validation<String, String> validateName(String name) {
            return CharSeq.of(name).replaceAll(VALID_NAME_CHARS, "").transform(seq -> seq.isEmpty()
                    ? Validation.valid(name)
                    : Validation.invalid("Name contains invalid characters: '"
                    + seq.distinct().sorted() + "'"));
        }

        private Validation<String, Integer> validateAge(int age) {
            return age < MIN_AGE
                    ? Validation.invalid("Age must be at least " + MIN_AGE)
                    : Validation.valid(age);
        }

    }

    class Person {

        public final String name;
        public final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person(" + name + ", " + age + ")";
        }

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
