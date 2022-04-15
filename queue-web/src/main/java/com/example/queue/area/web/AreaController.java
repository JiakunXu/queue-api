package com.example.queue.area.web;

import com.example.queue.area.api.AreaService;
import com.example.queue.area.api.bo.Area;
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
@RequestMapping(value = "/api/area")
public class AreaController extends BaseController {

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Area> list(HttpServletRequest request, HttpServletResponse response) {
        String code = this.getParameter(request, "code");
        String name = this.getParameter(request, "name");
        Area area = this.getParameter(request, new Area());

        int count = areaService.countArea(code, name, area);

        if (count == 0) {
            return new PageResponse<>(area.getPageNo(), area.getPageSize(), 0, null);
        }

        return new PageResponse<>(area.getPageNo(), area.getPageSize(), count,
            areaService.listAreas(code, name, area));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ObjectResponse<Area> get(HttpServletRequest request, HttpServletResponse response) {
        String id = this.getParameter(request, "id");
        return new ObjectResponse<>(areaService.getArea(id));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Area> save(HttpServletRequest request, HttpServletResponse response) {
        Area area = this.getParameter(request, Area.class);
        return new ObjectResponse<>(areaService.insertArea(area, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Area> update(HttpServletRequest request, HttpServletResponse response) {
        Area area = this.getParameter(request, Area.class);
        return new ObjectResponse<>(
            areaService.updateArea(area.getId(), area, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<Area> remove(HttpServletRequest request, HttpServletResponse response) {
        Area area = this.getParameter(request, Area.class);
        return new ObjectResponse<>(areaService.deleteArea(area.getId(), this.getUser().getName()));
    }

}
