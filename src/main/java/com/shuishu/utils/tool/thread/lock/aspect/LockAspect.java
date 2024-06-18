package com.shuishu.utils.tool.thread.lock.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @Author ：谁书-ss
 * @Date   ： 2024/6/18 9:54
 * @IDE    ：IntelliJ IDEA
 * @Motto  ：ABC(Always Be Coding)
 * <p></p>
 * @Description ： 切面拦截，添加并发控制Lock锁
 * @Order 越小越是最先执行，但更重要的是最先执行的最后结束
 * 使用时，要修改的代码：@Pointcut("@annotation(com.shuishu.utils.tool.thread.lock.ServiceLock)") ，换成项目实际路径
 */
@Order(1)
@Component
@Scope
@Aspect
public class LockAspect {
    private static final Logger logger = LoggerFactory.getLogger(LockAspect.class);

    /**
     * 互斥锁 参数默认false，不公平锁
     */
    private static final Lock LOCK = new ReentrantLock(true);

    /**
     * Service层切点，用于记录错误日志
     */
    @Pointcut("@annotation(com.shuishu.utils.tool.thread.lock.NiceServiceLock)")
    public void lockAspect() {
    }

    @Around("lockAspect()")
    public  Object around(ProceedingJoinPoint joinPoint) {
        boolean lockAcquired = false;
        try {
            lockAcquired = LOCK.tryLock(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Object obj = null;
        if (lockAcquired) {
            try {
                obj = joinPoint.proceed();
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            } finally{
                LOCK.unlock();
            }
        } else {
            throw new RuntimeException("系统繁忙，请稍后再试");
        }
        return obj;
    }

}
