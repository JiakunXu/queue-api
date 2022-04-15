package com.example.queue.nurse.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.nurse.api.NurseService;
import com.example.queue.nurse.api.bo.Nurse;
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
@RequestMapping(value = "/api/nurse")
public class NurseController extends BaseController {

    @Autowired
    private NurseService nurseService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Nurse> list(HttpServletRequest request, HttpServletResponse response) {
        String code = this.getParameter(request, "code");
        String name = this.getParameter(request, "name");
        Nurse nurse = this.getParameter(request, new Nurse());

        int count = nurseService.countNurse(code, name, nurse);

        if (count == 0) {
            return new PageResponse<>(nurse.getPageNo(), nurse.getPageSize(), 0, null);
        }

        return new PageResponse<>(nurse.getPageNo(), nurse.getPageSize(), count,
            nurseService.listNurses(code, name, nurse));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Nurse> save(HttpServletRequest request, HttpServletResponse response) {
        Nurse nurse = this.getParameter(request, Nurse.class);
        return new ObjectResponse<>(
            nurseService.insertNurse(nurse.getUserId(), nurse, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Nurse> update(HttpServletRequest request, HttpServletResponse response) {
        Nurse nurse = this.getParameter(request, Nurse.class);
        return new ObjectResponse<>(
            nurseService.updateNurse(nurse.getId(), nurse, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<Nurse> remove(HttpServletRequest request, HttpServletResponse response) {
        Nurse nurse = this.getParameter(request, Nurse.class);
        return new ObjectResponse<>(
            nurseService.deleteNurse(nurse.getId(), this.getUser().getName()));
    }

}
