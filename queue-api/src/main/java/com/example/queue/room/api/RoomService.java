package com.example.queue.room.api;

import com.example.queue.room.api.bo.Room;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface RoomService {

    /**
     * 未使用.
     */
    String STATUS_NOT_USED = "not_used";

    /**
     * 使用中.
     */
    String STATUS_USED     = "used";

    /**
     * 暂停中.
     */
    String STATUS_SUSPEND  = "suspend";

    /**
     *
     * @param deptId
     * @param code
     * @param name
     * @param status
     * @param room
     * @return
     */
    int countRoom(String deptId, String code, String name, String status, Room room);

    /**
     * 
     * @param deptId
     * @param code
     * @param name
     * @param status
     * @param room
     * @return
     */
    List<Room> listRooms(String deptId, String code, String name, String status, Room room);

    /**
     *
     * @param deptId
     * @param room
     * @param creator
     * @return
     */
    Room insertRoom(BigInteger deptId, Room room, String creator);

    /**
     *
     * @param id
     * @param room
     * @param modifier
     * @return
     */
    Room updateRoom(BigInteger id, Room room, String modifier);

    /**
     * 
     * @param id
     * @param status
     * @param modifier
     * @return
     */
    Room updateRoom(BigInteger id, String status, String modifier);

    /**
     * 
     * @param id
     * @param modifier
     * @return
     */
    Room deleteRoom(BigInteger id, String modifier);

}
