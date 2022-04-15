package com.example.queue.menu.service;

import com.alibaba.fastjson.JSON;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.menu.api.MenuService;
import com.example.queue.menu.api.bo.Menu;
import com.example.queue.menu.dao.dataobject.MenuDO;
import com.example.queue.menu.dao.mapper.MenuMapper;
import com.example.queue.role.api.RoleMenuService;
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
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private RoleMenuService     roleMenuService;

    @Autowired
    private MenuMapper          menuMapper;

    @Override
    public List<Menu> listMenus(String pid) {
        if (StringUtils.isBlank(pid)) {
            return null;
        }

        MenuDO menuDO = new MenuDO();
        menuDO.setPid(new BigInteger(pid));

        return BeanUtil.copy(list(menuDO), Menu.class);
    }

    @Override
    public Menu insertMenu(BigInteger pid, Menu menu, String creator) {
        if (pid == null || menu == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        menu.setPid(pid);

        MenuDO menuDO = BeanUtil.copy(menu, MenuDO.class);
        menuDO.setCreator(creator);

        try {
            menuMapper.insert(menuDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(menuDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        menu.setId(menuDO.getId());

        return menu;
    }

    @Override
    public Menu updateMenu(BigInteger id, Menu menu, String modifier) {
        if (id == null || menu == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        menu.setId(id);

        MenuDO menuDO = BeanUtil.copy(menu, MenuDO.class);
        menuDO.setModifier(modifier);

        try {
            if (menuMapper.update(menuDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(JSON.toJSONString(menuDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return menu;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Menu deleteMenu(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        MenuDO menuDO = new MenuDO();
        menuDO.setId(id);
        menuDO.setModifier(modifier);

        try {
            menuMapper.delete(menuDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(menuDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        roleMenuService.deleteRoleMenu(null, id, modifier);

        return BeanUtil.copy(menuDO, Menu.class);
    }

    private List<MenuDO> list(MenuDO menuDO) {
        try {
            return menuMapper.list(menuDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(menuDO), e);
        }

        return null;
    }

}
