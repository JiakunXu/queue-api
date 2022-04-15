package com.example.queue.menu.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ListResponse;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.menu.api.MenuService;
import com.example.queue.menu.api.bo.Menu;
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
@RequestMapping(value = "/api/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ListResponse<Menu> list(HttpServletRequest request, HttpServletResponse response) {
        String pid = this.getParameter(request, "pid");
        return new ListResponse<>(menuService.listMenus(pid));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Menu> save(HttpServletRequest request, HttpServletResponse response) {
        Menu menu = this.getParameter(request, Menu.class);
        return new ObjectResponse<>(
            menuService.insertMenu(menu.getPid(), menu, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Menu> update(HttpServletRequest request, HttpServletResponse response) {
        Menu menu = this.getParameter(request, Menu.class);
        return new ObjectResponse<>(
            menuService.updateMenu(menu.getId(), menu, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<Menu> remove(HttpServletRequest request, HttpServletResponse response) {
        Menu menu = this.getParameter(request, Menu.class);
        return new ObjectResponse<>(menuService.deleteMenu(menu.getId(), this.getUser().getName()));
    }

}
