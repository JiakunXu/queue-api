package com.example.queue.dict.manager;

import com.alibaba.fastjson.JSON;
import com.example.queue.cache.api.RedisService;
import com.example.queue.dict.api.DictService;
import com.example.queue.dict.api.bo.Dict;
import com.example.queue.dict.dao.dataobject.DictDO;
import com.example.queue.dict.dao.mapper.DictMapper;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
@Service
public class DictServiceImpl implements DictService {

    private static final Logger          logger = LoggerFactory.getLogger(DictServiceImpl.class);

    @Autowired
    private RedisService<String, Object> redisService;

    @Autowired
    private DictMapper                   dictMapper;

    @Override
    public List<Dict> listDicts(BigInteger dictTypeId) {
        if (dictTypeId == null) {
            return null;
        }

        DictDO dictDO = new DictDO();
        dictDO.setDictTypeId(dictTypeId);

        return BeanUtil.copy(list(dictDO), Dict.class);
    }

    @Override
    public List<Dict> listDicts(String dictTypeValue) {
        if (StringUtils.isBlank(dictTypeValue)) {
            return null;
        }

        String key = dictTypeValue;

        List<Dict> list = null;

        try {
            list = (List<Dict>) redisService.get(RedisService.CACHE_KEY_DICT_TYPE + key);
        } catch (ServiceException e) {
            logger.error(RedisService.CACHE_KEY_DICT_TYPE + key, e);
        }

        if (list != null && list.size() > 0) {
            return list;
        }

        DictDO dictDO = new DictDO();
        dictDO.setDictTypeValue(dictTypeValue);

        list = BeanUtil.copy(list(dictDO), Dict.class);

        if (list == null || list.size() == 0) {
            return null;
        }

        try {
            redisService.set(RedisService.CACHE_KEY_DICT_TYPE + key, list);
        } catch (ServiceException e) {
            logger.error(RedisService.CACHE_KEY_DICT_TYPE + key, e);
        }

        return list;
    }

    @Override
    public Dict getDict(String dictTypeValue, String value) {
        if (StringUtils.isBlank(dictTypeValue) || StringUtils.isBlank(value)) {
            return null;
        }

        String key = dictTypeValue + "&" + value;

        Dict dict = null;

        try {
            dict = (Dict) redisService.get(RedisService.CACHE_KEY_DICT_TYPE + key);
        } catch (ServiceException e) {
            logger.error(RedisService.CACHE_KEY_DICT_TYPE + key, e);
        }

        if (dict != null) {
            return dict;
        }

        DictDO dictDO = new DictDO();
        dictDO.setDictTypeValue(dictTypeValue);
        dictDO.setValue(value);

        dict = BeanUtil.copy(get(dictDO), Dict.class);

        if (dict == null) {
            return null;
        }

        try {
            redisService.set(RedisService.CACHE_KEY_DICT_TYPE + key, dict);
        } catch (ServiceException e) {
            logger.error(RedisService.CACHE_KEY_DICT_TYPE + key, e);
        }

        return dict;
    }

    @Override
    public Dict insertDict(BigInteger dictTypeId, Dict dict, String creator) {
        if (dictTypeId == null || dict == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        dict.setDictTypeId(dictTypeId);

        DictDO dictDO = BeanUtil.copy(dict, DictDO.class);
        dictDO.setCreator(creator);

        try {
            dictMapper.insert(dictDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(dictDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        dict.setId(dictDO.getId());

        return dict;
    }

    @Override
    public Dict updateDict(BigInteger id, Dict dict, String modifier) {
        if (id == null || dict == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        dict.setId(id);

        DictDO dictDO = BeanUtil.copy(dict, DictDO.class);
        dictDO.setModifier(modifier);

        try {
            if (dictMapper.update0(dictDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(JSON.toJSONString(dictDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return dict;
    }

    @Override
    public Dict updateDict(BigInteger dictTypeId, String dictTypeValue, String modifier) {
        if (dictTypeId == null || StringUtils.isBlank(dictTypeValue)
            || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        DictDO dictDO = new DictDO();
        dictDO.setDictTypeId(dictTypeId);
        dictDO.setDictTypeValue(dictTypeValue);
        dictDO.setModifier(modifier);

        try {
            dictMapper.update1(dictDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(dictDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(dictDO, Dict.class);
    }

    @Override
    public Dict deleteDict(BigInteger dictTypeId, BigInteger id, String modifier) {
        if ((dictTypeId == null && id == null) || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        DictDO dictDO = new DictDO();
        dictDO.setId(id);
        dictDO.setDictTypeId(dictTypeId);
        dictDO.setModifier(modifier);

        try {
            dictMapper.delete(dictDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(dictDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(dictDO, Dict.class);
    }

    private List<DictDO> list(DictDO dictDO) {
        try {
            return dictMapper.list(dictDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(dictDO), e);
        }

        return null;
    }

    private DictDO get(DictDO dictDO) {
        try {
            return dictMapper.get(dictDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(dictDO), e);
        }

        return null;
    }

}
