package com.example.queue.doctor.service;

import com.alibaba.fastjson.JSON;
import com.example.queue.cache.api.RedisService;
import com.example.queue.doctor.api.DoctorService;
import com.example.queue.doctor.api.bo.Doctor;
import com.example.queue.doctor.dao.dataobject.DoctorDO;
import com.example.queue.doctor.dao.mapper.DoctorMapper;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
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
public class DoctorServiceImpl implements DoctorService {

    private static final Logger          logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Autowired
    private RedisService<String, Doctor> redisService;

    @Autowired
    private DoctorMapper                 doctorMapper;

    @Override
    public int countDoctor(String code, String name, Doctor doctor) {
        if (doctor == null) {
            return 0;
        }

        doctor.setCode(code);
        doctor.setName(name);

        return count(BeanUtil.copy(doctor, DoctorDO.class));
    }

    @Override
    public List<Doctor> listDoctors(String code, String name, Doctor doctor) {
        if (doctor == null) {
            return null;
        }

        doctor.setCode(code);
        doctor.setName(name);

        return BeanUtil.copy(list(BeanUtil.copy(doctor, DoctorDO.class)), Doctor.class);
    }

    @Override
    public Doctor getDoctor(BigInteger id) {
        if (id == null) {
            return null;
        }

        String key = id.toString();

        Doctor doctor = null;

        try {
            doctor = redisService.get(RedisService.CACHE_KEY_DOCTOR_ID + key);
        } catch (ServiceException e) {
            logger.error(RedisService.CACHE_KEY_DOCTOR_ID + key, e);
        }

        if (doctor != null) {
            return doctor;
        }

        DoctorDO doctorDO = new DoctorDO();
        doctorDO.setId(id);

        doctor = BeanUtil.copy(get(doctorDO), Doctor.class);

        if (doctor == null) {
            return null;
        }

        try {
            redisService.set(RedisService.CACHE_KEY_DOCTOR_ID + key, doctor);
        } catch (ServiceException e) {
            logger.error(RedisService.CACHE_KEY_DOCTOR_ID + key, e);
        }

        return doctor;
    }

    @Override
    public Doctor insertDoctor(BigInteger userId, Doctor doctor, String creator) {
        if (userId == null || doctor == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        doctor.setUserId(userId);

        DoctorDO doctorDO = BeanUtil.copy(doctor, DoctorDO.class);
        doctorDO.setCreator(creator);

        try {
            doctorMapper.insert(doctorDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(doctorDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        doctor.setId(doctorDO.getId());

        return doctor;
    }

    @Override
    public Doctor updateDoctor(BigInteger id, Doctor doctor, String modifier) {
        if (id == null || doctor == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        doctor.setId(id);

        DoctorDO doctorDO = BeanUtil.copy(doctor, DoctorDO.class);
        doctorDO.setModifier(modifier);

        try {
            if (doctorMapper.update(doctorDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(JSON.toJSONString(doctorDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return doctor;
    }

    @Override
    public Doctor deleteDoctor(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        DoctorDO doctorDO = new DoctorDO();
        doctorDO.setId(id);
        doctorDO.setModifier(modifier);

        try {
            doctorMapper.delete(doctorDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(doctorDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(doctorDO, Doctor.class);
    }

    private int count(DoctorDO doctorDO) {
        try {
            return doctorMapper.count(doctorDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(doctorDO), e);
        }

        return 0;
    }

    private List<DoctorDO> list(DoctorDO doctorDO) {
        try {
            return doctorMapper.list(doctorDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(doctorDO), e);
        }

        return null;
    }

    private DoctorDO get(DoctorDO doctorDO) {
        try {
            return doctorMapper.get(doctorDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(doctorDO), e);
        }

        return null;
    }

}
