package com.cosmo.repository.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRepository.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void setKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Long lpush(String key, String val) throws Exception {
        return redisTemplate.opsForList().leftPush(key, val);
    }

    public String rpop(String key) throws Exception {
        return redisTemplate.opsForList().rightPop(key);
    }

}
