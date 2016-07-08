package com.SCC.environment.dao;

import com.SCC.environment.model.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by ZJDX on 2016/7/8.
 */
@Repository
public class MapDao {
    @Autowired
    private RedisTemplate<String,Map> redisTemplate;

    public void save(Map map) {
        /*redisTemplate.opsForList();
        redisTemplate.opsForSet();
        redisTemplate.opsForHash()*/
        ValueOperations<String, Map> valueOper = redisTemplate.opsForValue();
        valueOper.set(String.valueOf(map.getId()), map);
    }

    public Map read(int id) {
        ValueOperations<String, Map> valueOper = redisTemplate.opsForValue();
        return valueOper.get(String.valueOf(id));
    }

    public void delete(int id) {
        ValueOperations<String, Map> valueOper = redisTemplate.opsForValue();
        RedisOperations<String, Map> RedisOperations  = valueOper.getOperations();
        RedisOperations.delete(String.valueOf(id));
    }
}
