package com.example.queue.role.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ListResponse;
import com.example.queue.role.api.RoleMenuService;
import com.example.queue.role.api.bo.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JiakunXu
 */
@RestController
@RequestMapping(value = "/api/role/menu")
public class RoleMenuController extends BaseController {

    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ListResponse<RoleMenu> list(HttpServletRequest request, HttpServletResponse response) {
        String roleId = this.getParameter(request, "roleId");
        return new ListResponse<>(roleMenuService.listRoleMenus(roleId));
    }

}
