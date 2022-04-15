package com.example.queue.room.service;

import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.room.api.RoomService;
import com.example.queue.room.api.bo.Room;
import com.example.queue.room.dao.dataobject.RoomDO;
import com.example.queue.room.dao.mapper.RoomMapper;
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
public class RoomServiceImpl implements RoomService {

    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    @Autowired
    private RoomMapper          roomMapper;

    @Override
    public int countRoom(String deptId, String code, String name, String status, Room room) {
        if (StringUtils.isBlank(deptId) || room == null) {
            return 0;
        }

        room.setDeptId(new BigInteger(deptId));
        room.setCode(code);
        room.setName(name);
        room.setStatus(status);

        return count(BeanUtil.copy(room, RoomDO.class));
    }

    @Override
    public List<Room> listRooms(String deptId, String code, String name, String status, Room room) {
        if (StringUtils.isBlank(deptId) || room == null) {
            return null;
        }

        room.setDeptId(new BigInteger(deptId));
        room.setCode(code);
        room.setName(name);
        room.setStatus(status);

        return BeanUtil.copy(list(BeanUtil.copy(room, RoomDO.class)), Room.class);
    }

    @Override
    public Room insertRoom(BigInteger deptId, Room room, String creator) {
        if (deptId == null || room == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        room.setDeptId(deptId);

        RoomDO roomDO = BeanUtil.copy(room, RoomDO.class);
        roomDO.setCreator(creator);

        try {
            roomMapper.insert(roomDO);
        } catch (Exception e) {
            logger.error(roomDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        room.setId(roomDO.getId());

        return room;
    }

    @Override
    public Room updateRoom(BigInteger id, Room room, String modifier) {
        if (id == null || room == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        room.setId(id);

        RoomDO roomDO = BeanUtil.copy(room, RoomDO.class);
        roomDO.setModifier(modifier);

        try {
            if (roomMapper.update0(roomDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(roomDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return room;
    }

    @Override
    public Room updateRoom(BigInteger id, String status, String modifier) {
        if (id == null || StringUtils.isBlank(status) || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        RoomDO roomDO = new RoomDO();
        roomDO.setId(id);
        roomDO.setStatus(status);
        roomDO.setModifier(modifier);

        try {
            if (roomMapper.update1(roomDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(roomDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(roomDO, Room.class);
    }

    @Override
    public Room deleteRoom(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        RoomDO roomDO = new RoomDO();
        roomDO.setId(id);
        roomDO.setModifier(modifier);

        try {
            roomMapper.delete(roomDO);
        } catch (Exception e) {
            logger.error(roomDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(roomDO, Room.class);
    }

    private int count(RoomDO roomDO) {
        try {
            return roomMapper.count(roomDO);
        } catch (Exception e) {
            logger.error(roomDO.toString(), e);
        }

        return 0;
    }

    private List<RoomDO> list(RoomDO roomDO) {
        try {
            return roomMapper.list(roomDO);
        } catch (Exception e) {
            logger.error(roomDO.toString(), e);
        }

        return null;
    }

}
