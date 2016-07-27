package com.SCC.environment.service;

import org.springframework.stereotype.Service;

/**
 * Created by ZJDX on 2016/7/8.
 */
@Service("redisService")
public class RedisService implements IRedisService {
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }

}
