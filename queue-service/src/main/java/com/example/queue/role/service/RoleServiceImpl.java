package com.example.queue.role.service;

import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.role.api.RoleMenuService;
import com.example.queue.role.api.RoleService;
import com.example.queue.role.api.bo.Role;
import com.example.queue.role.dao.dataobject.RoleDO;
import com.example.queue.role.dao.mapper.RoleMapper;
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
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMenuService     roleMenuService;

    @Autowired
    private RoleMapper          roleMapper;

    @Override
    public int countRole(String code, String name, Role role) {
        if (role == null) {
            return 0;
        }

        role.setCode(code);
        role.setName(name);

        return count(BeanUtil.copy(role, RoleDO.class));
    }

    @Override
    public List<Role> listRoles(String code, String name, Role role) {
        if (role == null) {
            return null;
        }

        role.setCode(code);
        role.setName(name);

        return BeanUtil.copy(list(BeanUtil.copy(role, RoleDO.class)), Role.class);
    }

    @Override
    public Role getRole(BigInteger id) {
        if (id == null) {
            return null;
        }

        RoleDO roleDO = new RoleDO();
        roleDO.setId(id);

        return BeanUtil.copy(get(roleDO), Role.class);
    }

    @Override
    public Role insetRole(Role role, String creator) {
        if (role == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        RoleDO roleDO = BeanUtil.copy(role, RoleDO.class);
        roleDO.setCreator(creator);

        try {
            roleMapper.insert(roleDO);
        } catch (Exception e) {
            logger.error(roleDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        role.setId(roleDO.getId());

        return role;
    }

    @Override
    public Role updateRole(BigInteger id, Role role, String modifier) {
        if (id == null || role == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        role.setId(id);

        RoleDO roleDO = BeanUtil.copy(role, RoleDO.class);
        roleDO.setModifier(modifier);

        try {
            if (roleMapper.update(roleDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(roleDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return role;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Role deleteRole(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        RoleDO roleDO = new RoleDO();
        roleDO.setId(id);
        roleDO.setModifier(modifier);

        try {
            roleMapper.delete(roleDO);
        } catch (Exception e) {
            logger.error(roleDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        roleMenuService.deleteRoleMenu(id, null, modifier);

        return BeanUtil.copy(roleDO, Role.class);
    }

    private int count(RoleDO roleDO) {
        try {
            return roleMapper.count(roleDO);
        } catch (Exception e) {
            logger.error(roleDO.toString(), e);
        }

        return 0;
    }

    private List<RoleDO> list(RoleDO roleDO) {
        try {
            return roleMapper.list(roleDO);
        } catch (Exception e) {
            logger.error(roleDO.toString(), e);
        }

        return null;
    }

    private RoleDO get(RoleDO roleDO) {
        try {
            return roleMapper.get(roleDO);
        } catch (Exception e) {
            logger.error(roleDO.toString(), e);
        }

        return null;
    }

}
