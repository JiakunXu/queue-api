package com.example.queue.role.api;

import com.example.queue.role.api.bo.Role;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface RoleService {

    /**
     * 
     * @param code
     * @param name
     * @param role
     * @return
     */
    int countRole(String code, String name, Role role);

    /**
     *
     * @param code
     * @param name
     * @param role
     * @return
     */
    List<Role> listRoles(String code, String name, Role role);

    /**
     * 
     * @param id
     * @return
     */
    Role getRole(BigInteger id);

    /**
     *
     * @param role
     * @param creator
     * @return
     */
    Role insetRole(Role role, String creator);

    /**
     *
     * @param id
     * @param role
     * @param modifier
     * @return
     */
    Role updateRole(BigInteger id, Role role, String modifier);

    /**
     * 
     * @param id
     * @param modifier
     * @return
     */
    Role deleteRole(BigInteger id, String modifier);

}
