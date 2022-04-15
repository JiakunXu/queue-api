package com.example.queue.cache.api;

import com.example.queue.framework.exception.ServiceException;

/**
 * Redis.
 *
 * @author JiakunXu
 */
public interface RedisService<K, V> extends CacheService<K, V> {

    /**
     * default_exp_time.
     */
    long   DEFAULT_EXP         = 24 * 60 * 60;

    String CACHE_KEY_DEPT_ID   = "key_dept_id_";

    String CACHE_KEY_DICT_TYPE = "key_dict_type_";

    String CACHE_KEY_DOCTOR_ID = "key_doctor_id_";

    String CACHE_KEY_USER_ID   = "key_user_id_";

    /**
     * incr.
     *
     * @param key
     * @param delta
     * @return
     * @throws ServiceException
     */
    Long incr(K key, long delta) throws ServiceException;

}
