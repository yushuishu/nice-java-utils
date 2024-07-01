package com.shuishu.utils.tool.cache.redis.config;


/**
 * @author wuZhenFeng
 * @date 2024/6/27 9:28
 */


import com.shuishu.utils.tool.cache.redis.NiceRedis;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/27 9:28
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 * <p></p>
 * 执行时机
 * PostConstruct > InitializingBean > ApplicationRunner > CommandLineRunner
 */
@Component
public class RedisApplicationRunner implements ApplicationRunner {

    @Resource
    private RedisServiceConfig redisServiceConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        redisServiceConfig.setUpRedisTemplate(new NiceRedis());
    }

}
