package com.example.queue.room.api;

import com.example.queue.room.api.bo.RoomNurse;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface RoomNurseService {

    /**
     * 
     * @param roomId
     * @param roomNurse
     * @return
     */
    int countRoomNurse(String roomId, RoomNurse roomNurse);

    /**
     *
     * @param roomId
     * @param roomNurse
     * @return
     */
    List<RoomNurse> listRoomNurses(String roomId, RoomNurse roomNurse);

    /**
     * 
     * @param roomId
     * @param nurseId
     * @param creator
     * @return
     */
    RoomNurse insertRoomNurse(BigInteger roomId, BigInteger nurseId, String creator);

}
