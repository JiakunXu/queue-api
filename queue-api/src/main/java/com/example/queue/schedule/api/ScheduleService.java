package com.example.queue.schedule.api;

import com.example.queue.schedule.api.bo.Schedule;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface ScheduleService {

    /**
     * 
     * @param deptId
     * @param startDate
     * @param endDate
     * @return
     */
    List<Schedule> listSchedules(String deptId, String startDate, String endDate);

    /**
     *
     * @param deptId
     * @param schedule
     * @param creator
     * @return
     */
    Schedule insertSchedule(BigInteger deptId, Schedule schedule, String creator);

    /**
     *
     * @param id
     * @param schedule
     * @param modifier
     * @return
     */
    Schedule updateSchedule(BigInteger id, Schedule schedule, String modifier);

    /**
     * 
     * @param id
     * @param modifier
     * @return
     */
    Schedule deleteSchedule(BigInteger id, String modifier);

}
