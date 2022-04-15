package com.example.queue.role.service;

import com.alibaba.fastjson.JSON;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.role.api.RoleMenuService;
import com.example.queue.role.api.bo.RoleMenu;
import com.example.queue.role.dao.dataobject.RoleMenuDO;
import com.example.queue.role.dao.mapper.RoleMenuMapper;
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
public class RoleMenuServiceImpl implements RoleMenuService {

    private static final Logger logger = LoggerFactory.getLogger(RoleMenuServiceImpl.class);

    @Autowired
    private RoleMenuMapper      roleMenuMapper;

    @Override
    public List<RoleMenu> listRoleMenus(String roleId) {
        if (StringUtils.isBlank(roleId)) {
            return null;
        }

        RoleMenuDO roleMenuDO = new RoleMenuDO();
        roleMenuDO.setRoleId(new BigInteger(roleId));

        return BeanUtil.copy(list(roleMenuDO), RoleMenu.class);
    }

    @Override
    public RoleMenu deleteRoleMenu(BigInteger roleId, BigInteger menuId, String modifier) {
        if ((roleId == null && menuId == null) || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        RoleMenuDO roleMenuDO = new RoleMenuDO();
        roleMenuDO.setRoleId(roleId);
        roleMenuDO.setMenuId(menuId);
        roleMenuDO.setModifier(modifier);

        try {
            roleMenuMapper.delete(roleMenuDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(roleMenuDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(roleMenuDO, RoleMenu.class);
    }

    private List<RoleMenuDO> list(RoleMenuDO roleMenuDO) {
        try {
            return roleMenuMapper.list(roleMenuDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(roleMenuDO), e);
        }

        return null;
    }

}
