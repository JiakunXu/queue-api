package com.example.queue.user.service;

import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.framework.util.EncryptUtil;
import com.example.queue.user.api.UserService;
import com.example.queue.user.api.bo.User;
import com.example.queue.user.dao.dataobject.UserDO;
import com.example.queue.user.dao.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper          userMapper;

    @Override
    public User getUser(BigInteger id) {
        if (id == null) {
            return null;
        }

        UserDO userDO = new UserDO();
        userDO.setId(id);

        return BeanUtil.copy(get(userDO), User.class);
    }

    @Override
    public User getUser(String passport, String password) {
        if (StringUtils.isBlank(passport) || StringUtils.isBlank(password)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "账号或密码不能为空");
        }

        UserDO userDO = new UserDO();
        userDO.setPassword(password);

        userDO = get(userDO);

        if (userDO == null) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "该用户在系统中不存在");
        }

        try {
            if (!password.equals(EncryptUtil.encryptSHA(password))) {
                throw new ServiceException(Constants.BUSINESS_FAILED, "账号或密码输入不正确");
            }
        } catch (IOException e) {
            logger.error(password, e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "系统正忙，请稍后再试");
        }

        return BeanUtil.copy(userDO, User.class);
    }

    @Override
    public User insertUser(User user, String creator) {
        if (user == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        UserDO userDO = BeanUtil.copy(user, UserDO.class);
        userDO.setCreator(creator);

        try {
            userMapper.insert(userDO);
        } catch (Exception e) {
            logger.error(userDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        user.setId(userDO.getId());

        return user;
    }

    @Override
    public User updateUser(BigInteger id, User user, String modifier) {
        if (id == null || user == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        user.setId(id);

        UserDO userDO = BeanUtil.copy(user, UserDO.class);
        userDO.setModifier(modifier);

        try {
            if (userMapper.update(userDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(userDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return user;
    }

    private UserDO get(UserDO userDO) {
        try {
            return userMapper.get(userDO);
        } catch (Exception e) {
            logger.error(userDO.toString(), e);
        }

        return null;
    }

}
