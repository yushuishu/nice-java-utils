package com.shuishu.base.utils.thread;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/18 17:43
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <br>
 * @Description ：多线程
 * <br>
 */
public class NiceThreadPoolTest {

    public static void main(String[] args) {

    }

    /**
     * 从线程池中获取线程，执行任务，无返回值
     *
     * 两个任务输出，是交叉输出
     */
    @Test
    public void execute() {
        List<String> li = new ArrayList<>();
        System.out.println(li.get(0));
        // 任务1
        NiceThreadPool.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("执行任务1");
            }
        });
        // 任务2
        NiceThreadPool.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("执行任务2");
            }
        });
    }

    /**
     * 从线程池中获取线程，执行任务，有返回值，直接获取结果
     */
    @Test
    public void submit1() {
        Future<String> future = NiceThreadPool.submit(() -> {
            return "submit result";
        });
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从线程池中获取线程，执行任务，有返回值，直接获取结果，如果超过 3000毫秒，则获取结果失败
     */
    @Test
    public void submit2() {
        Future<String> future = NiceThreadPool.submit(() -> {
            // 模拟延时 5000 毫秒
            Thread.sleep(5000);
            return "submit result";
        });
        try {
            // 设置获取返回值的 超时时间 3000 毫秒，报错信息：java.lang.RuntimeException: java.util.concurrent.TimeoutException
            String result = future.get(3000, TimeUnit.MILLISECONDS);
            System.out.println(result);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

}
