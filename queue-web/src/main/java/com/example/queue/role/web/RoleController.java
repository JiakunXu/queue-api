package com.example.queue.role.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.role.api.RoleService;
import com.example.queue.role.api.bo.Role;
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
@RequestMapping(value = "/api/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Role> list(HttpServletRequest request, HttpServletResponse response) {
        String code = this.getParameter(request, "code");
        String name = this.getParameter(request, "name");
        Role role = this.getParameter(request, new Role());

        int count = roleService.countRole(code, name, role);

        if (count == 0) {
            return new PageResponse<>(role.getPageNo(), role.getPageSize(), 0, null);
        }

        return new PageResponse<>(role.getPageNo(), role.getPageSize(), count,
            roleService.listRoles(code, name, role));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Role> save(HttpServletRequest request, HttpServletResponse response) {
        Role role = this.getParameter(request, Role.class);
        return new ObjectResponse<>(roleService.insetRole(role, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Role> update(HttpServletRequest request, HttpServletResponse response) {
        Role role = this.getParameter(request, Role.class);
        return new ObjectResponse<>(
            roleService.updateRole(role.getId(), role, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<Role> remove(HttpServletRequest request, HttpServletResponse response) {
        Role role = this.getParameter(request, Role.class);
        return new ObjectResponse<>(roleService.deleteRole(role.getId(), this.getUser().getName()));
    }

}
