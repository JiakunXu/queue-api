package com.example.queue.room.dao.mapper;

import com.example.queue.framework.mapper.BaseMapper;
import com.example.queue.room.dao.dataobject.RoomDO;

/**
 * @author JiakunXu
 */
public interface RoomMapper extends BaseMapper<RoomDO> {

    /**
     *
     * @param roomDO
     * @return
     */
    int update0(RoomDO roomDO);

    /**
     *
     * @param roomDO
     * @return
     */
    int update1(RoomDO roomDO);

}
