package com.shuishu.utils.tool.thread.lock.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author ：谁书-ss
 * @Date   ： 2024/6/18 9:43
 * @IDE    ：IntelliJ IDEA
 * @Motto  ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：分布式锁。使用在 多个方法之间 控制并发，加锁的方法上
 * <p></p>
 * 参考：
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NiceServiceDistributedLock {
    /**
     * 锁key
     *
     * @return -
     */
    String lockKey();
    /**
     * 锁value
     *
     * @return -
     */
    String lockValue();
    /**
     * 获取锁，重试超时时间，默认10秒，
     *
     * @return -
     */
    int tryLock() default 10;
    /**
     * 业务加锁描述
     *
     * @return -
     */
    String description() default "";
}
