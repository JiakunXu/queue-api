package com.example.queue.doctor.web;

import com.example.queue.doctor.api.DoctorService;
import com.example.queue.doctor.api.bo.Doctor;
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
@RequestMapping(value = "/api/doctor")
public class DoctorController extends BaseController {

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Doctor> list(HttpServletRequest request, HttpServletResponse response) {
        String code = this.getParameter(request, "code");
        String name = this.getParameter(request, "name");
        Doctor doctor = this.getParameter(request, new Doctor());

        int count = doctorService.countDoctor(code, name, doctor);

        if (count == 0) {
            return new PageResponse<>(doctor.getPageNo(), doctor.getPageSize(), 0, null);
        }

        return new PageResponse<>(doctor.getPageNo(), doctor.getPageSize(), count,
            doctorService.listDoctors(code, name, doctor));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Doctor> save(HttpServletRequest request, HttpServletResponse response) {
        Doctor doctor = this.getParameter(request, Doctor.class);
        return new ObjectResponse<>(
            doctorService.insertDoctor(doctor.getUserId(), doctor, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Doctor> update(HttpServletRequest request, HttpServletResponse response) {
        Doctor doctor = this.getParameter(request, Doctor.class);
        return new ObjectResponse<>(
            doctorService.updateDoctor(doctor.getId(), doctor, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<Doctor> remove(HttpServletRequest request, HttpServletResponse response) {
        Doctor doctor = this.getParameter(request, Doctor.class);
        return new ObjectResponse<>(
            doctorService.deleteDoctor(doctor.getId(), this.getUser().getName()));
    }

}
