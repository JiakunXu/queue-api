package com.example.queue.cache.api;

import com.example.queue.framework.exception.ServiceException;

import java.util.Date;

/**
 * cacheService.
 * 
 * @author xujiakun
 * 
 * @param <K>
 * @param <V>
 */
public interface CacheService<K, V> {

    /**
     * 保存数据.
     * 
     * @param key
     * @param value
     * @return
     * @throws ServiceException
     */
    V add(K key, V value) throws ServiceException;

    /**
     * 保存有有效期的数据.
     * 
     * @param key
     * @param value
     * @param 有效期
     * @return
     * @throws ServiceException
     */
    V add(K key, V value, Date expiry) throws ServiceException;

    /**
     * 保存有有效期的数据.
     * 
     * @param key
     * @param value
     * @param 数据超时的秒数
     * @return
     * @throws ServiceException
     */
    V add(K key, V value, long timeout) throws ServiceException;

    /**
     * 保存数据.
     * 
     * @param key
     * @param value
     * @return
     * @throws ServiceException
     */
    V set(K key, V value) throws ServiceException;

    /**
     * 保存有有效期的数据.
     * 
     * @param key
     * @param value
     * @param 有效期
     * @return
     * @throws ServiceException
     */
    V set(K key, V value, Date expiry) throws ServiceException;

    /**
     * 保存有有效期的数据.
     * 
     * @param key
     * @param value
     * @param 数据超时的秒数
     * @return
     * @throws ServiceException
     */
    V set(K key, V value, long timeout) throws ServiceException;

    /**
     * 保存数据,前提是key必须存在于memcache中，否则保存不成功.
     * 
     * @param key
     * @param value
     * @return
     * @throws ServiceException
     */
    V replace(K key, V value) throws ServiceException;

    /**
     * 保存有有效期的数据，前提是key必须存在于memcache中，否则保存不成功.
     * 
     * @param key
     * @param value
     * @param 有效期
     * @return
     * @throws ServiceException
     */
    V replace(K key, V value, Date expiry) throws ServiceException;

    /**
     * 保存有有效期的数据，前提是key必须存在于memcache中，否则保存不成功.
     * 
     * @param key
     * @param value
     * @param 有效期
     * @return
     * @throws ServiceException
     */
    V replace(K key, V value, long timeout) throws ServiceException;

    /**
     * 获取缓存数据.
     * 
     * @param key
     * @return
     * @throws ServiceException
     */
    V get(K key) throws ServiceException;

    /**
     * 移出缓存数据.
     * 
     * @param key
     * @return
     * @throws ServiceException
     */
    void remove(K key) throws ServiceException;

}
