package com.example.queue.app.service;

import com.alibaba.fastjson.JSON;
import com.example.queue.app.api.AppService;
import com.example.queue.app.api.bo.App;
import com.example.queue.app.dao.dataobject.AppDO;
import com.example.queue.app.dao.mapper.AppMapper;
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
public class AppServiceImpl implements AppService {

    private static final Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);

    @Autowired
    private AppMapper           appMapper;

    @Override
    public int countApp(String name, App app) {
        if (app == null) {
            return 0;
        }

        app.setName(name);

        return count(BeanUtil.copy(app, AppDO.class));
    }

    @Override
    public List<App> listApps(String name, App app) {
        if (app == null) {
            return null;
        }

        app.setName(name);

        return BeanUtil.copy(list(BeanUtil.copy(app, AppDO.class)), App.class);
    }

    @Override
    public App getApp(String id) {
        if (StringUtils.isNotBlank(id)) {
            return null;
        }

        AppDO appDO = new AppDO();
        appDO.setId(new BigInteger(id));

        return BeanUtil.copy(get(appDO), App.class);
    }

    @Override
    public App insertApp(App app, String creator) {
        if (app == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        AppDO appDO = BeanUtil.copy(app, AppDO.class);
        appDO.setCreator(creator);

        try {
            appMapper.insert(appDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(appDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        app.setId(appDO.getId());

        return app;
    }

    @Override
    public App updateApp(BigInteger id, App app, String modifier) {
        if (id == null || app == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        app.setId(id);

        AppDO appDO = BeanUtil.copy(app, AppDO.class);
        appDO.setModifier(modifier);

        try {
            if (appMapper.update(appDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(JSON.toJSONString(appDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return app;
    }

    private int count(AppDO appDO) {
        try {
            return appMapper.count(appDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(appDO), e);
        }

        return 0;
    }

    private List<AppDO> list(AppDO appDO) {
        try {
            return appMapper.list(appDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(appDO), e);
        }

        return null;
    }

    private AppDO get(AppDO appDO) {
        try {
            return appMapper.get(appDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(appDO), e);
        }

        return null;
    }

}
