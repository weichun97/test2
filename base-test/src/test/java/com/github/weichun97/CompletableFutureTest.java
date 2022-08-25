package com.github.weichun97;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 参考链接：https://www.baeldung.com/java-completablefuture
 */
public class CompletableFutureTest {

    @Test
    public void test() throws InterruptedException, ExecutionException {
        Future<String> completableFuture = calculateAsync();
        Thread.sleep(500);
        System.out.println("异步中其他操作");
        String result = completableFuture.get();
        System.out.println("异步结果：" + result);
        assertEquals("Hello", result);
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        Future<String> completableFuture = CompletableFuture.completedFuture("Hello");
        Thread.sleep(500);
        System.out.println("异步中其他操作");
        String result = completableFuture.get();
        System.out.println("异步结果：" + result);
        assertEquals("Hello", result);
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> System.out.println("123"));

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "2123");
        String s = stringCompletableFuture.get();
        System.out.println(s);
    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> future = completableFuture
                .thenApply(s -> s + " World");

        assertEquals("Hello World", future.get());
    }

    @Test
    public void test5() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        completableFuture.thenAccept(s -> System.out.println("Computation returned: " + s));
    }

    @Test
    public void test6() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        completableFuture.thenRun(() -> System.out.println("Computation finished."));
    }

    @Test
    public void test7() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
        assertEquals("Hello World", completableFuture.get());
    }

    @Test
    public void test8() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(
                        () -> " World"), (s1, s2) -> s1 + s2);

        assertEquals("Hello World", completableFuture.get());
    }

    @Test
    public void test9(){
        CompletableFuture future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"),
                        (s1, s2) -> System.out.println(s1 + s2));
    }

    @Test
    public void testThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> finalResult = compute(1).thenApply(s-> s + 1);
        assertEquals(Integer.valueOf(2), finalResult.get());
    }

    @Test
    public void testThenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> finalResult = compute(1).thenCompose(this::computeAnother);
        assertEquals(Integer.valueOf(2), finalResult.get());
    }

    @Test
    public void testAllOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);
        combinedFuture.get();
        assertTrue(future1.isDone());
        assertTrue(future2.isDone());
        assertTrue(future3.isDone());

        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        assertEquals("Hello Beautiful World", combined);
    }

    @Test
    public void testException() throws ExecutionException, InterruptedException {
        String name = null;
        CompletableFuture<String> completableFuture
                =  CompletableFuture.supplyAsync(() -> {
                    if (name == null) {
                        throw new RuntimeException("Computation error!");
                    }
                    return "Hello, " + name;
                }).handle((s, t) -> s != null ? s : "Hello, Stranger!");
        assertEquals("Hello, Stranger!", completableFuture.get());

        // 选择自定义的异常
        CompletableFuture<String> completableFuture2 = new CompletableFuture<>();
        // ...
        completableFuture2.completeExceptionally(new RuntimeException("Calculation failed!"));
        // ...
        completableFuture2.get(); // ExecutionException
    }

    @Test
    public void testAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> future = completableFuture
                .thenApplyAsync(s -> s + " World");

        assertEquals("Hello World", future.get());
    }

    CompletableFuture<Integer> compute(Integer i){
        return CompletableFuture.supplyAsync(() -> i);
    }

    CompletableFuture<Integer> computeAnother(Integer i){
        return CompletableFuture.supplyAsync(() -> 1 + i);
    }

    public Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            System.out.println("异步操作");
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });
        return completableFuture;
    }
}
