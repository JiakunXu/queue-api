package com.example.queue.schedule.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ListResponse;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.schedule.api.ScheduleService;
import com.example.queue.schedule.api.bo.Schedule;
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
@RequestMapping(value = "/api/schedule")
public class ScheduleController extends BaseController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ListResponse<Schedule> list(HttpServletRequest request, HttpServletResponse response) {
        String deptId = this.getParameter(request, "deptId");
        String startDate = this.getParameter(request, "startDate");
        String endDate = this.getParameter(request, "endDate");
        return new ListResponse<>(scheduleService.listSchedules(deptId, startDate, endDate));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Schedule> save(HttpServletRequest request, HttpServletResponse response) {
        Schedule schedule = this.getParameter(request, Schedule.class);
        return new ObjectResponse<>(scheduleService.insertSchedule(schedule.getDeptId(), schedule,
            this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Schedule> update(HttpServletRequest request,
                                           HttpServletResponse response) {
        Schedule schedule = this.getParameter(request, Schedule.class);
        return new ObjectResponse<>(
            scheduleService.updateSchedule(schedule.getId(), schedule, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<Schedule> remove(HttpServletRequest request,
                                           HttpServletResponse response) {
        Schedule schedule = this.getParameter(request, Schedule.class);
        return new ObjectResponse<>(
            scheduleService.deleteSchedule(schedule.getId(), this.getUser().getName()));
    }

}
