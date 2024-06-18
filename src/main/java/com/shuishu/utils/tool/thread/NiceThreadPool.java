package com.shuishu.utils.tool.thread;


import com.shuishu.utils.tool.thread.factory.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;


/**
 * @Author ：谁书-ss
 * @Date   ： 2024/6/18 9:50
 * @IDE    ：IntelliJ IDEA
 * @Motto  ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：线程池
 *
 * 参考：https://www.cnblogs.com/liangxianning/p/17058248.html
 *      com.google.guava
 */
public final class NiceThreadPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(NiceThreadPool.class);
    private static ThreadPoolExecutor threadPool = null;
    /**
     * 线程名称
     */
    private static final String POOL_NAME = "nice-thread-pool";
    /**
     * 等待队列长度
     */
    private static final int BLOCKING_QUEUE_LENGTH = 100;
    /**
     * 空闲线程，存活时间
     */
    private static final int KEEP_ALIVE_TIME = 60 * 1000;

    private NiceThreadPool() {
        throw new IllegalStateException("utility class");
    }


    /**
     * 无返回值直接执行
     *
     * 使用方法：NiceThreadPool.execute(() -> {});
     *
     * @param runnable 需要运行的任务
     */
    public static void execute(Runnable runnable) {
        getThreadPool().execute(runnable);
    }

    /**
     * 有返回值执行
     * 主线程中使用 Future.get()获取返回值时，会阻塞主线程，直到任务执行完毕
     *
     * 使用方法：1、调用：Future<String> future = NiceThreadPool.submit(() -> {return "返回值"});
     *         2、获取值：String val = future.get(3, TimeUnit.SECONDS) //获取值的 超时时间
     *
     * @param callable 需要运行的任务
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return getThreadPool().submit(callable);
    }

    private static synchronized ThreadPoolExecutor getThreadPool() {
        if (threadPool == null) {
            // 获取处理器数量
            int cpuNum = Runtime.getRuntime().availableProcessors();
            // 根据cpu数量,计算出合理的线程并发数（服务器部署的服务只有一个，计算最大线程数为：cpuNum * 2）
            int maximumPoolSize = cpuNum / 2 + cpuNum;
            // 核心线程数、最大线程数、闲置线程存活时间、时间单位、线程队列、线程工厂、当前线程数已经超过最大线程数时的异常处理策略
            threadPool = new ThreadPoolExecutor(maximumPoolSize - 1,
                    maximumPoolSize,
                    KEEP_ALIVE_TIME,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingDeque<>(BLOCKING_QUEUE_LENGTH),
                    new ThreadFactoryBuilder().setNameFormat(POOL_NAME + "-%d").build(),
                    new ThreadPoolExecutor.AbortPolicy() {
                        @Override
                        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                            LOGGER.warn("线程爆炸了，当前运行线程总数：{}，活动线程数：{}。等待队列已满，等待运行任务数：{}",
                                    e.getPoolSize(),
                                    e.getActiveCount(),
                                    e.getQueue().size());
                        }
                    });

        }

        return threadPool;
    }


}
