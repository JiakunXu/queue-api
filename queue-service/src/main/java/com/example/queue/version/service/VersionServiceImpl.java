package com.example.queue.version.service;

import com.alibaba.fastjson.JSON;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.version.api.VersionService;
import com.example.queue.version.api.bo.Version;
import com.example.queue.version.dao.dataobject.VersionDO;
import com.example.queue.version.dao.mapper.VersionMapper;
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
public class VersionServiceImpl implements VersionService {

    private static final Logger logger = LoggerFactory.getLogger(VersionServiceImpl.class);

    @Autowired
    private VersionMapper       versionMapper;

    @Override
    public int countVersion(String appId, Version version) {
        if (version == null) {
            return 0;
        }

        if (StringUtils.isNotBlank(appId)) {
            version.setAppId(new BigInteger(appId));
        }

        return count(BeanUtil.copy(version, VersionDO.class));
    }

    @Override
    public List<Version> listVersions(String appId, Version version) {
        if (version == null) {
            return null;
        }

        if (StringUtils.isNotBlank(appId)) {
            version.setAppId(new BigInteger(appId));
        }

        return BeanUtil.copy(list(BeanUtil.copy(version, VersionDO.class)), Version.class);
    }

    @Override
    public Version getVersion(String appId, String client) {
        if (StringUtils.isBlank(appId) || StringUtils.isBlank(client)) {
            return null;
        }

        VersionDO versionDO = new VersionDO();
        versionDO.setAppId(new BigInteger(appId));
        versionDO.setClient(client);

        return BeanUtil.copy(get(versionDO), Version.class);
    }

    @Override
    public Version insertVersion(BigInteger appId, String client, String url, String creator) {
        if (appId == null || StringUtils.isBlank(client) || StringUtils.isBlank(url)
            || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        VersionDO versionDO = new VersionDO();
        versionDO.setAppId(appId);
        versionDO.setClient(client);
        versionDO.setUrl(url);
        versionDO.setCreator(creator);

        try {
            versionMapper.insert(versionDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(versionDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        return BeanUtil.copy(get(versionDO), Version.class);
    }

    @Override
    public Version updateVersion(BigInteger id, String url, String modifier) {
        if (id == null || StringUtils.isBlank(url) || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        VersionDO versionDO = new VersionDO();
        versionDO.setId(id);
        versionDO.setUrl(url);
        versionDO.setModifier(modifier);

        try {
            if (versionMapper.update(versionDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(JSON.toJSONString(versionDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(get(versionDO), Version.class);
    }

    private int count(VersionDO versionDO) {
        try {
            return versionMapper.count(versionDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(versionDO), e);
        }

        return 0;
    }

    private List<VersionDO> list(VersionDO versionDO) {
        try {
            return versionMapper.list(versionDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(versionDO), e);
        }

        return null;
    }

    private VersionDO get(VersionDO versionDO) {
        try {
            return versionMapper.get(versionDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(versionDO), e);
        }

        return null;
    }

}
