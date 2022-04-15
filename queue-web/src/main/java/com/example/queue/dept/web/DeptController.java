package com.example.queue.dept.web;

import com.example.queue.dept.api.DeptService;
import com.example.queue.dept.api.bo.Dept;
import com.example.queue.framework.web.BaseController;
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
@RequestMapping(value = "/api/dept")
public class DeptController extends BaseController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Dept> list(HttpServletRequest request, HttpServletResponse response) {
        String code = this.getParameter(request, "code");
        String name = this.getParameter(request, "name");
        Dept dept = this.getParameter(request, new Dept());

        int count = deptService.countDept(code, name, dept);

        if (count == 0) {
            return new PageResponse<>(dept.getPageNo(), dept.getPageSize(), 0, null);
        }

        return new PageResponse<>(dept.getPageNo(), dept.getPageSize(), count,
            deptService.listDepts(code, name, dept));
    }

}
