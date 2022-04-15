package com.example.queue.schedule.service;

import com.example.queue.doctor.api.DoctorService;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.schedule.api.ScheduleService;
import com.example.queue.schedule.api.bo.Schedule;
import com.example.queue.schedule.dao.dataobject.ScheduleDO;
import com.example.queue.schedule.dao.mapper.ScheduleMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private DoctorService       doctorService;

    @Autowired
    private ScheduleMapper      scheduleMapper;

    @Override
    public List<Schedule> listSchedules(String deptId, String startDate, String endDate) {
        if (StringUtils.isBlank(deptId) || StringUtils.isBlank(startDate)
            || StringUtils.isBlank(endDate)) {
            return null;
        }

        ScheduleDO scheduleDO = new ScheduleDO();
        scheduleDO.setDeptId(new BigInteger(deptId));
        scheduleDO.setStartDate(startDate);
        scheduleDO.setEndDate(endDate);

        List<Schedule> list = BeanUtil.copy(list(scheduleDO), Schedule.class);

        if (list == null || list.size() == 0) {
            return null;
        }

        for (Schedule schedule : list) {
            schedule.setDoctor(doctorService.getDoctor(schedule.getDoctorId()));
        }

        return list;
    }

    @Override
    public Schedule insertSchedule(BigInteger deptId, Schedule schedule, String creator) {
        if (deptId == null || schedule == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        schedule.setDeptId(deptId);

        ScheduleDO scheduleDO = BeanUtil.copy(schedule, ScheduleDO.class);
        scheduleDO.setCreator(creator);

        try {
            scheduleMapper.insert(scheduleDO);
        } catch (Exception e) {
            logger.error(scheduleDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        schedule.setId(scheduleDO.getId());

        return schedule;
    }

    @Override
    public Schedule updateSchedule(BigInteger id, Schedule schedule, String modifier) {
        if (id == null || schedule == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        schedule.setId(id);

        ScheduleDO scheduleDO = BeanUtil.copy(schedule, ScheduleDO.class);
        scheduleDO.setModifier(modifier);

        try {
            if (scheduleMapper.update(scheduleDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(scheduleDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return schedule;
    }

    @Override
    public Schedule deleteSchedule(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        ScheduleDO scheduleDO = new ScheduleDO();
        scheduleDO.setId(id);
        scheduleDO.setModifier(modifier);

        try {
            scheduleMapper.delete(scheduleDO);
        } catch (Exception e) {
            logger.error(scheduleDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(scheduleDO, Schedule.class);
    }

    private List<ScheduleDO> list(ScheduleDO scheduleDO) {
        try {
            return scheduleMapper.list(scheduleDO);
        } catch (Exception e) {
            logger.error(scheduleDO.toString(), e);
        }

        return null;
    }

}
