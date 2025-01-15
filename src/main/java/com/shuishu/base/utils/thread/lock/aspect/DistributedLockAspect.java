package com.shuishu.base.utils.thread.lock.aspect;


import com.shuishu.base.utils.cache.redis.NiceRedis;
import com.shuishu.base.utils.thread.lock.annotation.NiceServiceDistributedLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author ：谁书-ss
 * @Date   ： 2024/6/27 9:54
 * @IDE    ：IntelliJ IDEA
 * @Motto  ：ABC(Always Be Coding)
 * <br>
 * @Description ： 切面拦截，添加并发控制Lock锁
 * <br>
 * 参考：
 *
 * 使用：
 * 要修改的代码：@Pointcut("@annotation(com.shuishu.utils.tool.thread.lock.NiceServiceDistributedLock)") ，换成项目实际路径
 */
//order越小越是最先执行，但更重要的是最先执行的最后结束
@Order(1)
@Component
@Scope
@Aspect
public class DistributedLockAspect {

    private static final Logger logger = LoggerFactory.getLogger(DistributedLockAspect.class);



    /**
     * Service层切点，用于记录错误日志
     */
    @Pointcut("@annotation(com.shuishu.base.utils.thread.lock.annotation.NiceServiceDistributedLock)")
    public void distributedLockAspect() {
    }

    @Around("distributedLockAspect()")
    public  Object around(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        NiceServiceDistributedLock niceServiceDistributedLock = method.getAnnotation(NiceServiceDistributedLock.class);
        String lockKey = niceServiceDistributedLock.lockKey();
        String lockValue = niceServiceDistributedLock.lockValue();
        int tryLock = niceServiceDistributedLock.tryLock();
        tryLock = Math.max(tryLock, 10);
        boolean lockAcquired = NiceRedis.lock(lockKey, lockValue, 20, tryLock);
        Object obj = null;
        if (lockAcquired) {
            try {
                obj = joinPoint.proceed();
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException(e.getMessage());
            } finally{
                NiceRedis.unLock(lockKey, lockValue);
            }
        } else {
            throw new RuntimeException("系统繁忙，请稍后再试");
        }
        return obj;
    }

}
