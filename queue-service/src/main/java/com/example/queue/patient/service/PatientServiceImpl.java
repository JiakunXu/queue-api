package com.example.queue.patient.service;

import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.patient.api.PatientService;
import com.example.queue.patient.api.bo.Patient;
import com.example.queue.patient.dao.dataobject.PatientDO;
import com.example.queue.patient.dao.mapper.PatientMapper;
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
public class PatientServiceImpl implements PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientMapper       patientMapper;

    @Override
    public int countPatient(String code, String name, Patient patient) {
        if (patient == null) {
            return 0;
        }

        patient.setCode(code);
        patient.setName(name);

        return count(BeanUtil.copy(patient, PatientDO.class));
    }

    @Override
    public List<Patient> listPatients(String code, String name, Patient patient) {
        if (patient == null) {
            return null;
        }

        patient.setCode(code);
        patient.setName(name);

        return BeanUtil.copy(list(BeanUtil.copy(patient, PatientDO.class)), Patient.class);
    }

    @Override
    public Patient getPatient(BigInteger id) {
        if (id == null) {
            return null;
        }

        PatientDO patientDO = new PatientDO();
        patientDO.setId(id);

        return BeanUtil.copy(get(patientDO), Patient.class);
    }

    @Override
    public Patient insertPatient(Patient patient, String creator) {
        if (patient == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        PatientDO patientDO = BeanUtil.copy(patient, PatientDO.class);
        patientDO.setCreator(creator);

        try {
            patientMapper.insert(patientDO);
        } catch (Exception e) {
            logger.error(patientDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        patient.setId(patientDO.getId());

        return patient;
    }

    @Override
    public Patient updatePatient(BigInteger id, Patient patient, String modifier) {
        if (id == null || patient == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        patient.setId(id);

        PatientDO patientDO = BeanUtil.copy(patient, PatientDO.class);
        patientDO.setModifier(modifier);

        try {
            if (patientMapper.update(patientDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(patientDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return patient;
    }

    private int count(PatientDO patientDO) {
        try {
            return patientMapper.count(patientDO);
        } catch (Exception e) {
            logger.error(patientDO.toString(), e);
        }

        return 0;
    }

    private List<PatientDO> list(PatientDO patientDO) {
        try {
            return patientMapper.list(patientDO);
        } catch (Exception e) {
            logger.error(patientDO.toString(), e);
        }

        return null;
    }

    private PatientDO get(PatientDO patientDO) {
        try {
            return patientMapper.get(patientDO);
        } catch (Exception e) {
            logger.error(patientDO.toString(), e);
        }

        return null;
    }

}
