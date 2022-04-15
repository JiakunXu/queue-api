package com.example.queue.app.web;

import com.example.queue.app.api.AppService;
import com.example.queue.app.api.bo.App;
import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.framework.response.PageResponse;
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
@RequestMapping(value = "/api/app")
public class AppController extends BaseController {

    @Autowired
    private AppService appService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<App> list(HttpServletRequest request, HttpServletResponse response) {
        String name = this.getParameter(request, "name");
        App app = this.getParameter(request, new App());

        int count = appService.countApp(name, app);

        if (count == 0) {
            return new PageResponse<>(app.getPageNo(), app.getPageSize(), 0, null);
        }

        return new PageResponse<>(app.getPageNo(), app.getPageSize(), count,
            appService.listApps(name, app));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<App> save(HttpServletRequest request, HttpServletResponse response) {
        App app = this.getParameter(request, App.class);
        return new ObjectResponse<>(appService.insertApp(app, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<App> update(HttpServletRequest request, HttpServletResponse response) {
        App app = this.getParameter(request, App.class);
        return new ObjectResponse<>(
            appService.updateApp(app.getId(), app, this.getUser().getName()));
    }

}
