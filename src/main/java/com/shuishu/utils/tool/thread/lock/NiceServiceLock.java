package com.shuishu.utils.tool.thread.lock;


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
    String description() default "";
}
