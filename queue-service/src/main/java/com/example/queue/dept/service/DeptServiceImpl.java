package com.example.queue.dept.service;

import com.example.queue.cache.api.RedisService;
import com.example.queue.dept.api.DeptService;
import com.example.queue.dept.api.bo.Dept;
import com.example.queue.dept.dao.dataobject.DeptDO;
import com.example.queue.dept.dao.mapper.DeptMapper;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.user.api.UserDeptService;
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
public class DeptServiceImpl implements DeptService {

    private static final Logger        logger = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Autowired
    private RedisService<String, Dept> redisService;

    @Autowired
    private UserDeptService            userDeptService;

    @Autowired
    private DeptMapper                 deptMapper;

    @Override
    public int countDept(String code, String name, Dept dept) {
        if (dept == null) {
            return 0;
        }

        dept.setCode(code);
        dept.setName(name);

        return count(BeanUtil.copy(dept, DeptDO.class));
    }

    @Override
    public List<Dept> listDepts(String code, String name, Dept dept) {
        if (dept == null) {
            return null;
        }

        dept.setCode(code);
        dept.setName(name);

        return BeanUtil.copy(list(BeanUtil.copy(dept, DeptDO.class)), Dept.class);
    }

    @Override
    public Dept getDept(BigInteger id) {
        if (id == null) {
            return null;
        }

        String key = id.toString();

        Dept dept = null;

        try {
            dept = redisService.get(RedisService.CACHE_KEY_DEPT_ID + key);
        } catch (ServiceException e) {
            logger.error(RedisService.CACHE_KEY_DEPT_ID + key, e);
        }

        if (dept != null) {
            return dept;
        }

        DeptDO deptDO = new DeptDO();
        deptDO.setId(id);

        dept = BeanUtil.copy(get(deptDO), Dept.class);

        if (dept == null) {
            return null;
        }

        try {
            redisService.set(RedisService.CACHE_KEY_DEPT_ID + key, dept);
        } catch (ServiceException e) {
            logger.error(RedisService.CACHE_KEY_DEPT_ID + key, e);
        }

        return dept;
    }

    @Override
    public Dept insertDept(Dept dept, String creator) {
        if (dept == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        DeptDO deptDO = BeanUtil.copy(dept, DeptDO.class);
        deptDO.setCreator(creator);

        try {
            deptMapper.insert(deptDO);
        } catch (Exception e) {
            logger.error(deptDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        dept.setId(deptDO.getId());

        return dept;
    }

    @Override
    public Dept updateDept(BigInteger id, Dept dept, String modifier) {
        if (id == null || dept == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        dept.setId(id);

        DeptDO deptDO = BeanUtil.copy(dept, DeptDO.class);
        deptDO.setModifier(modifier);

        try {
            if (deptMapper.update(deptDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(deptDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return dept;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Dept deleteDept(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        DeptDO deptDO = new DeptDO();
        deptDO.setId(id);
        deptDO.setModifier(modifier);

        try {
            deptMapper.delete(deptDO);
        } catch (Exception e) {
            logger.error(deptDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        userDeptService.deleteUserDept(id, modifier);

        return BeanUtil.copy(deptDO, Dept.class);
    }

    private int count(DeptDO deptDO) {
        try {
            return deptMapper.count(deptDO);
        } catch (Exception e) {
            logger.error(deptDO.toString(), e);
        }

        return 0;
    }

    private List<DeptDO> list(DeptDO deptDO) {
        try {
            return deptMapper.list(deptDO);
        } catch (Exception e) {
            logger.error(deptDO.toString(), e);
        }

        return null;
    }

    private DeptDO get(DeptDO deptDO) {
        try {
            return deptMapper.get(deptDO);
        } catch (Exception e) {
            logger.error(deptDO.toString(), e);
        }

        return null;
    }

}
