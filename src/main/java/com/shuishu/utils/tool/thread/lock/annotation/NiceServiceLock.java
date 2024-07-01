package com.shuishu.utils.tool.thread.lock.annotation;


import java.lang.annotation.*;

/**
 * @Author ：谁书-ss
 * @Date   ： 2024/6/18 9:54
 * @IDE    ：IntelliJ IDEA
 * @Motto  ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：使用在控制并发，要加锁的方法上
 * <p></p>
 * 参考：
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NiceServiceLock {
    /**
     * 业务加锁描述
     *
     * @return -
     */
    String description() default "";

    /**
     * 并发下，获取锁的等待时间，单位：秒。最少1秒，默认1秒
     *
     * @return -
     */
    int tryLock() default 1;

}
