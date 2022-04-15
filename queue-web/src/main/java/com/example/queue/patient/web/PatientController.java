package com.example.queue.patient.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.patient.api.PatientService;
import com.example.queue.patient.api.bo.Patient;
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
@RequestMapping(value = "/api/patient")
public class PatientController extends BaseController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Patient> list(HttpServletRequest request, HttpServletResponse response) {
        String code = this.getParameter(request, "code");
        String name = this.getParameter(request, "name");
        Patient patient = this.getParameter(request, new Patient());

        int count = patientService.countPatient(code, name, patient);

        if (count == 0) {
            return new PageResponse<>(patient.getPageNo(), patient.getPageSize(), 0, null);
        }

        return new PageResponse<>(patient.getPageNo(), patient.getPageSize(), count,
            patientService.listPatients(code, name, patient));
    }

}
