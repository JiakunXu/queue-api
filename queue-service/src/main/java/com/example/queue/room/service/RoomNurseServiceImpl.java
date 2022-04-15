package com.example.queue.room.service;

import com.alibaba.fastjson.JSON;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.room.api.RoomNurseService;
import com.example.queue.room.api.bo.RoomNurse;
import com.example.queue.room.dao.dataobject.RoomNurseDO;
import com.example.queue.room.dao.mapper.RoomNurseMapper;
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
public class RoomNurseServiceImpl implements RoomNurseService {

    private static final Logger logger = LoggerFactory.getLogger(RoomNurseServiceImpl.class);

    @Autowired
    private RoomNurseMapper     roomNurseMapper;

    @Override
    public int countRoomNurse(String roomId, RoomNurse roomNurse) {
        if (StringUtils.isBlank(roomId) || roomNurse == null) {
            return 0;
        }

        roomNurse.setRoomId(new BigInteger(roomId));

        return count(BeanUtil.copy(roomNurse, RoomNurseDO.class));
    }

    @Override
    public List<RoomNurse> listRoomNurses(String roomId, RoomNurse roomNurse) {
        if (StringUtils.isBlank(roomId) || roomNurse == null) {
            return null;
        }

        roomNurse.setRoomId(new BigInteger(roomId));

        return BeanUtil.copy(list(BeanUtil.copy(roomNurse, RoomNurseDO.class)), RoomNurse.class);
    }

    @Override
    public RoomNurse insertRoomNurse(BigInteger roomId, BigInteger nurseId, String creator) {
        if (roomId == null || nurseId == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        RoomNurseDO roomNurseDO = new RoomNurseDO();
        roomNurseDO.setRoomId(roomId);
        roomNurseDO.setNurseId(nurseId);
        roomNurseDO.setCreator(creator);

        try {
            roomNurseMapper.insert(roomNurseDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(roomNurseDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        return BeanUtil.copy(roomNurseDO, RoomNurse.class);
    }

    private int count(RoomNurseDO roomNurseDO) {
        try {
            return roomNurseMapper.count(roomNurseDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(roomNurseDO), e);
        }

        return 0;
    }

    private List<RoomNurseDO> list(RoomNurseDO roomNurseDO) {
        try {
            return roomNurseMapper.list(roomNurseDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(roomNurseDO), e);
        }

        return null;
    }

}
