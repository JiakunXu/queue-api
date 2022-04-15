package com.example.queue.role.api;

import com.example.queue.role.api.bo.RoleMenu;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface RoleMenuService {

    /**
     *
     * @param roleId
     * @return
     */
    List<RoleMenu> listRoleMenus(String roleId);

    /**
     *
     * @param roleId
     * @param menuId
     * @param modifier
     * @return
     */
    RoleMenu deleteRoleMenu(BigInteger roleId, BigInteger menuId, String modifier);

}
