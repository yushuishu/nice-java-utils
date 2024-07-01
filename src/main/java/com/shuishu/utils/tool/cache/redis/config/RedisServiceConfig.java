package com.shuishu.utils.tool.cache.redis.config;


import com.shuishu.utils.tool.cache.redis.NiceRedis;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/27 9:23
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 * <p></p>
 */
@Component
public class RedisServiceConfig {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void setUpRedisTemplate(NiceRedis niceRedis) {
        niceRedis.setRedisTemplate(redisTemplate);
    }

}
