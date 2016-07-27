package com.SCC.environment.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by ZJDX on 2016/7/8.
 */
@Repository
public class RedisDao {
    private static Logger logger = LoggerFactory.getLogger(RedisDao.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void save(String key,Object object) {
        /*redisTemplate.opsForList();
        redisTemplate.opsForSet();
        redisTemplate.opsForHash()*/
        logger.info("save:"+key);
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        valueOper.set(key, object);
    }

    public Object read(String key) {
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        return valueOper.get(key);
    }

    public void delete(String key) {
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        RedisOperations<String, Object> RedisOperations  = valueOper.getOperations();
        RedisOperations.delete(key);
    }
}
