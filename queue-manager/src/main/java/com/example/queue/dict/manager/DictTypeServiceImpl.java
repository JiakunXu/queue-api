package com.example.queue.dict.manager;

import com.alibaba.fastjson.JSON;
import com.example.queue.cache.api.RedisService;
import com.example.queue.dict.api.DictService;
import com.example.queue.dict.api.DictTypeService;
import com.example.queue.dict.api.bo.DictType;
import com.example.queue.dict.dao.dataobject.DictTypeDO;
import com.example.queue.dict.dao.mapper.DictTypeMapper;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
@Service
public class DictTypeServiceImpl implements DictTypeService {

    private static final Logger            logger = LoggerFactory
        .getLogger(DictTypeServiceImpl.class);

    @Autowired
    private RedisService<String, DictType> redisService;

    @Autowired
    private DictService                    dictService;

    @Autowired
    private DictTypeMapper                 dictTypeMapper;

    @Override
    public int countDictType(String name, String value, DictType dictType) {
        if (dictType == null) {
            return 0;
        }

        dictType.setName(name);
        dictType.setValue(value);

        return count(BeanUtil.copy(dictType, DictTypeDO.class));
    }

    @Override
    public List<DictType> listDictTypes(String name, String value, DictType dictType) {
        if (dictType == null) {
            return null;
        }

        dictType.setName(name);
        dictType.setValue(value);

        return BeanUtil.copy(list(BeanUtil.copy(dictType, DictTypeDO.class)), DictType.class);
    }

    @Override
    public DictType getDictType(BigInteger id) {
        if (id == null) {
            return null;
        }

        DictTypeDO dictTypeDO = new DictTypeDO();
        dictTypeDO.setId(id);

        return BeanUtil.copy(get(dictTypeDO), DictType.class);
    }

    @Override
    public DictType getDictType(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }

        String key = value;

        DictType dictType = null;

        try {
            dictType = redisService.get(RedisService.CACHE_KEY_DICT_TYPE + key);
        } catch (ServiceException e) {
            logger.error(RedisService.CACHE_KEY_DICT_TYPE + key, e);
        }

        if (dictType != null) {
            return dictType;
        }

        DictTypeDO dictTypeDO = new DictTypeDO();
        dictTypeDO.setValue(value);

        dictType = BeanUtil.copy(get(dictTypeDO), DictType.class);

        if (dictType == null) {
            return null;
        }

        try {
            redisService.set(RedisService.CACHE_KEY_DICT_TYPE + key, dictType);
        } catch (ServiceException e) {
            logger.error(RedisService.CACHE_KEY_DICT_TYPE + key, e);
        }

        return dictType;
    }

    @Override
    public DictType insertDictType(DictType dictType, String creator) {
        if (dictType == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        DictTypeDO dictTypeDO = BeanUtil.copy(dictType, DictTypeDO.class);
        dictTypeDO.setCreator(creator);

        try {
            dictTypeMapper.insert(dictTypeDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(dictTypeDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        dictType.setId(dictTypeDO.getId());

        return dictType;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public DictType updateDictType(BigInteger id, DictType dictType, String modifier) {
        if (id == null || dictType == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        dictType.setId(id);

        DictTypeDO dictTypeDO = BeanUtil.copy(dictType, DictTypeDO.class);
        dictTypeDO.setModifier(modifier);

        try {
            if (dictTypeMapper.update(dictTypeDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(JSON.toJSONString(dictTypeDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        dictService.updateDict(id, dictType.getValue(), modifier);

        return dictType;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public DictType deleteDictType(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        DictTypeDO dictTypeDO = new DictTypeDO();
        dictTypeDO.setId(id);
        dictTypeDO.setModifier(modifier);

        try {
            dictTypeMapper.delete(dictTypeDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(dictTypeDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        dictService.deleteDict(id, null, modifier);

        return BeanUtil.copy(dictTypeDO, DictType.class);
    }

    private int count(DictTypeDO dictTypeDO) {
        try {
            return dictTypeMapper.count(dictTypeDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(dictTypeDO), e);
        }

        return 0;
    }

    private List<DictTypeDO> list(DictTypeDO dictTypeDO) {
        try {
            return dictTypeMapper.list(dictTypeDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(dictTypeDO), e);
        }

        return null;
    }

    private DictTypeDO get(DictTypeDO dictTypeDO) {
        try {
            return dictTypeMapper.get(dictTypeDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(dictTypeDO), e);
        }

        return null;
    }

}
