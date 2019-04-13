package com.sixplus.demo.service.impl;

import com.sixplus.demo.entity.Cart;
import com.sixplus.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis操作Service的实现类
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate<String, List<Cart>> redisTemplate;

    @Override
    public void set(String key, List<Cart> value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public List<Cart> get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean expire(String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key,delta);
    }

    @Override
    public boolean exists(String key)
    {
        return redisTemplate.opsForValue().get(key)!=null;
    }

    @Override
    public void getAndSet(String key,List<Cart> value)
    {
        redisTemplate.opsForValue().getAndSet(key,value);
    }
}
