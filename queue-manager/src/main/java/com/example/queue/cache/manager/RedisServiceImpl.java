package com.example.queue.cache.manager;

import com.example.queue.cache.api.RedisService;
import com.example.queue.framework.exception.ServiceException;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author JiakunXu
 */
@Service
public class RedisServiceImpl<K, V> implements RedisService<K, V> {

    private static final Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Resource
    private RedisTemplate<K, V> redisTemplate;

    @Override
    public V add(K key, V value) throws ServiceException {
        return add(key, value, RedisService.DEFAULT_EXP);
    }

    @Override
    public V add(K key, V value, Date expiry) throws ServiceException {
        if (expiry == null) {
            return add(key, value);
        }

        return add(key, value,
            Seconds.secondsBetween(new DateTime(new Date()), new DateTime(expiry)).getSeconds());
    }

    @Override
    public V add(K key, V value, long timeout) throws ServiceException {
        try {
            Boolean result = redisTemplate.opsForValue().setIfAbsent(key, value, timeout,
                TimeUnit.SECONDS);
            if (result != null && result) {
                return value;
            }
        } catch (Exception e) {
            logger.error("add", e);
        }

        throw new ServiceException("redis add.");
    }

    @Override
    public V set(K key, V value) throws ServiceException {
        return set(key, value, RedisService.DEFAULT_EXP);
    }

    @Override
    public V set(K key, V value, Date expiry) throws ServiceException {
        if (expiry == null) {
            return set(key, value);
        }

        return set(key, value,
            Seconds.secondsBetween(new DateTime(new Date()), new DateTime(expiry)).getSeconds());
    }

    @Override
    public V set(K key, V value, long timeout) throws ServiceException {
        try {
            redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
            return value;
        } catch (Exception e) {
            logger.error("set", e);
        }

        throw new ServiceException("redis set.");
    }

    @Override
    public V replace(K key, V value) throws ServiceException {
        return null;
    }

    @Override
    public V replace(K key, V value, Date expiry) throws ServiceException {
        return null;
    }

    @Override
    public V replace(K key, V value, long timeout) throws ServiceException {
        return null;
    }

    @Override
    public V get(K key) throws ServiceException {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            logger.error("get", e);
        }

        throw new ServiceException("redis get.");
    }

    @Override
    public void remove(K key) throws ServiceException {
        try {
            redisTemplate.delete(key);
            return;
        } catch (Exception e) {
            logger.error("remove", e);
        }

        throw new ServiceException("redis remove.");
    }

    @Override
    public Long incr(K key, long delta) throws ServiceException {
        try {
            return redisTemplate.opsForValue().increment(key, delta);
        } catch (Exception e) {
            logger.error("incr", e);
        }

        throw new ServiceException("redis incr.");
    }

}
