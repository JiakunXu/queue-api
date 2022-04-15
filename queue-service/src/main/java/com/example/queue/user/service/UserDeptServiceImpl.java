package com.example.queue.user.service;

import com.alibaba.fastjson.JSON;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.user.api.UserDeptService;
import com.example.queue.user.api.UserService;
import com.example.queue.user.api.bo.UserDept;
import com.example.queue.user.dao.dataobject.UserDeptDO;
import com.example.queue.user.dao.mapper.UserDeptMapper;
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
public class UserDeptServiceImpl implements UserDeptService {

    private static final Logger logger = LoggerFactory.getLogger(UserDeptServiceImpl.class);

    @Autowired
    private UserService         userService;

    @Autowired
    private UserDeptMapper      userDeptMapper;

    @Override
    public int countUserDept(String deptId, UserDept userDept) {
        if (StringUtils.isBlank(deptId) || userDept == null) {
            return 0;
        }

        userDept.setDeptId(new BigInteger(deptId));

        return count(BeanUtil.copy(userDept, UserDeptDO.class));
    }

    @Override
    public List<UserDept> listUserDepts(String deptId, UserDept userDept) {
        if (StringUtils.isBlank(deptId) || userDept == null) {
            return null;
        }

        userDept.setDeptId(new BigInteger(deptId));

        List<UserDept> list = BeanUtil.copy(list(BeanUtil.copy(userDept, UserDeptDO.class)),
            UserDept.class);

        if (list == null || list.size() == 0) {
            return null;
        }

        for (UserDept ud : list) {
            ud.setUser(userService.getUser(ud.getUserId()));
        }

        return list;
    }

    @Override
    public List<UserDept> listUserDepts(BigInteger userId) {
        if (userId == null) {
            return null;
        }

        UserDeptDO userDeptDO = new UserDeptDO();
        userDeptDO.setUserId(userId);
        userDeptDO.setPageNo(1);
        userDeptDO.setPageSize(10);

        return BeanUtil.copy(list(userDeptDO), UserDept.class);
    }

    @Override
    public UserDept deleteUserDept(BigInteger deptId, String modifier) {
        if (deptId == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        UserDeptDO userDeptDO = new UserDeptDO();
        userDeptDO.setDeptId(deptId);
        userDeptDO.setModifier(modifier);

        try {
            userDeptMapper.delete(userDeptDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(userDeptDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(userDeptDO, UserDept.class);
    }

    private int count(UserDeptDO userDeptDO) {
        try {
            return userDeptMapper.count(userDeptDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(userDeptDO), e);
        }

        return 0;
    }

    private List<UserDeptDO> list(UserDeptDO userDeptDO) {
        try {
            return userDeptMapper.list(userDeptDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(userDeptDO), e);
        }

        return null;
    }

}
