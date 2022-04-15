package com.example.queue.user.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.user.api.UserDeptService;
import com.example.queue.user.api.bo.UserDept;
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
@RequestMapping(value = "/api/user/dept")
public class UserDeptController extends BaseController {

    @Autowired
    private UserDeptService userDeptService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<UserDept> list(HttpServletRequest request, HttpServletResponse response) {
        String deptId = this.getParameter(request, "deptId");
        UserDept userDept = this.getParameter(request, new UserDept());

        int count = userDeptService.countUserDept(deptId, userDept);

        if (count == 0) {
            return new PageResponse<>(userDept.getPageNo(), userDept.getPageSize(), 0, null);
        }

        return new PageResponse<>(userDept.getPageNo(), userDept.getPageSize(), count,
            userDeptService.listUserDepts(deptId, userDept));
    }

}
