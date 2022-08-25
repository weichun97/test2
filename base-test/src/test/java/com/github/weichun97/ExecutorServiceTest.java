package com.github.weichun97;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

public class ExecutorServiceTest {

    Runnable runnableTask = () -> {
        try {
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println("运行无返回值方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    Callable<String> callableTask = () -> {
        TimeUnit.MILLISECONDS.sleep(300);
        return "Task's execution";
    };

    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(runnableTask);

        Future<String> future =
                executorService.submit(callableTask);
        assertEquals("Task's execution", future.get());

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        String result = executorService.invokeAny(callableTasks);
        assertEquals("Task's execution", future.get());
        List<Future<String>> futures = executorService.invokeAll(callableTasks);

    }

    @Test
    public void testShutDown(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                List<Runnable> runnables = executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            List<Runnable> runnables = executorService.shutdownNow();
        }
    }

    @Test
    public void testScheduleExecutor(){
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        Future<String> resultFuture = executorService.schedule(callableTask, 1, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(runnableTask, 100, 450, TimeUnit.MILLISECONDS);
    }
}
