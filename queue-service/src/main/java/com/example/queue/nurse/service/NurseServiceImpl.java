package com.example.queue.nurse.service;

import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.nurse.api.NurseService;
import com.example.queue.nurse.api.bo.Nurse;
import com.example.queue.nurse.dao.dataobject.NurseDO;
import com.example.queue.nurse.dao.mapper.NurseMapper;
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
public class NurseServiceImpl implements NurseService {

    private static final Logger logger = LoggerFactory.getLogger(NurseServiceImpl.class);

    @Autowired
    private NurseMapper         nurseMapper;

    @Override
    public int countNurse(String code, String name, Nurse nurse) {
        if (nurse == null) {
            return 0;
        }

        nurse.setCode(code);
        nurse.setName(name);

        return count(BeanUtil.copy(nurse, NurseDO.class));
    }

    @Override
    public List<Nurse> listNurses(String code, String name, Nurse nurse) {
        if (nurse == null) {
            return null;
        }

        nurse.setCode(code);
        nurse.setName(name);

        return BeanUtil.copy(list(BeanUtil.copy(nurse, NurseDO.class)), Nurse.class);
    }

    @Override
    public Nurse getNurse(BigInteger id) {
        if (id == null) {
            return null;
        }

        NurseDO nurseDO = new NurseDO();
        nurseDO.setId(id);

        return BeanUtil.copy(get(nurseDO), Nurse.class);
    }

    @Override
    public Nurse insertNurse(BigInteger userId, Nurse nurse, String creator) {
        if (userId == null || nurse == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        nurse.setUserId(userId);

        NurseDO nurseDO = BeanUtil.copy(nurse, NurseDO.class);
        nurseDO.setCreator(creator);

        try {
            nurseMapper.insert(nurseDO);
        } catch (Exception e) {
            logger.error(nurseDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        nurse.setId(nurseDO.getId());

        return nurse;
    }

    @Override
    public Nurse updateNurse(BigInteger id, Nurse nurse, String modifier) {
        if (id == null || nurse == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        nurse.setId(id);

        NurseDO nurseDO = BeanUtil.copy(nurse, NurseDO.class);
        nurseDO.setModifier(modifier);

        try {
            if (nurseMapper.update(nurseDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(nurseDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return nurse;
    }

    @Override
    public Nurse deleteNurse(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        NurseDO nurseDO = new NurseDO();
        nurseDO.setId(id);
        nurseDO.setModifier(modifier);

        try {
            nurseMapper.delete(nurseDO);
        } catch (Exception e) {
            logger.error(nurseDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(nurseDO, Nurse.class);
    }

    private int count(NurseDO nurseDO) {
        try {
            return nurseMapper.count(nurseDO);
        } catch (Exception e) {
            logger.error(nurseDO.toString(), e);
        }

        return 0;
    }

    private List<NurseDO> list(NurseDO nurseDO) {
        try {
            return nurseMapper.list(nurseDO);
        } catch (Exception e) {
            logger.error(nurseDO.toString(), e);
        }

        return null;
    }

    private NurseDO get(NurseDO nurseDO) {
        try {
            return nurseMapper.get(nurseDO);
        } catch (Exception e) {
            logger.error(nurseDO.toString(), e);
        }

        return null;
    }

}
