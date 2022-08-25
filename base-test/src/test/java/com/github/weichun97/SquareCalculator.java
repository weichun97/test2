package com.github.weichun97;

import org.junit.Test;

import java.util.concurrent.*;

public class SquareCalculator {
    
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private ExecutorService executor2 = Executors.newFixedThreadPool(2);
    
    public Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }

    public Future<Integer> calculate2(Integer input) {
        return executor2.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }

    @Test
    public void test() throws InterruptedException, ExecutionException, TimeoutException {
        Future<Integer> future = calculate(10);
        future.cancel(false);
//        Integer result = future.get(500, TimeUnit.MILLISECONDS);
        while(!future.isDone()) {
            System.out.println("Calculating...");
            Thread.sleep(300);
        }
        Integer result = future.get();
        System.out.println(result);

    }

    @Test
    public void test2() throws InterruptedException, ExecutionException {
        Future<Integer> future1 = calculate2(10);
        Future<Integer> future2 = calculate2(100);

        while (!(future1.isDone() && future2.isDone())) {
            System.out.println(
                    String.format(
                            "future1 is %s and future2 is %s",
                            future1.isDone() ? "done" : "not done",
                            future2.isDone() ? "done" : "not done"
                    )
            );
            Thread.sleep(300);
        }

        Integer result1 = future1.get();
        Integer result2 = future2.get();

        System.out.println(result1 + " and " + result2);
    }
}