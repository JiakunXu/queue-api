package com.example.queue.room.service;

import com.alibaba.fastjson.JSON;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.room.api.RoomDoctorService;
import com.example.queue.room.api.bo.RoomDoctor;
import com.example.queue.room.dao.dataobject.RoomDoctorDO;
import com.example.queue.room.dao.mapper.RoomDoctorMapper;
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
public class RoomDoctorServiceImpl implements RoomDoctorService {

    private static final Logger logger = LoggerFactory.getLogger(RoomDoctorServiceImpl.class);

    @Autowired
    private RoomDoctorMapper    roomDoctorMapper;

    @Override
    public int countRoomDoctor(String roomId, RoomDoctor roomDoctor) {
        if (StringUtils.isBlank(roomId) || roomDoctor == null) {
            return 0;
        }

        roomDoctor.setRoomId(new BigInteger(roomId));

        return count(BeanUtil.copy(roomDoctor, RoomDoctorDO.class));
    }

    @Override
    public List<RoomDoctor> listRoomDoctors(String roomId, RoomDoctor roomDoctor) {
        if (StringUtils.isBlank(roomId) || roomDoctor == null) {
            return null;
        }

        roomDoctor.setRoomId(new BigInteger(roomId));

        return BeanUtil.copy(list(BeanUtil.copy(roomDoctor, RoomDoctorDO.class)), RoomDoctor.class);
    }

    @Override
    public RoomDoctor insertRoomDoctor(BigInteger roomId, BigInteger doctorId, String creator) {
        if (roomId == null || doctorId == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        RoomDoctorDO roomDoctorDO = new RoomDoctorDO();
        roomDoctorDO.setRoomId(roomId);
        roomDoctorDO.setDoctorId(doctorId);
        roomDoctorDO.setCreator(creator);

        try {
            roomDoctorMapper.insert(roomDoctorDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(roomDoctorDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        return BeanUtil.copy(roomDoctorDO, RoomDoctor.class);
    }

    private int count(RoomDoctorDO roomDoctorDO) {
        try {
            return roomDoctorMapper.count(roomDoctorDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(roomDoctorDO), e);
        }

        return 0;
    }

    private List<RoomDoctorDO> list(RoomDoctorDO roomDoctorDO) {
        try {
            return roomDoctorMapper.list(roomDoctorDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(roomDoctorDO), e);
        }

        return null;
    }

}
