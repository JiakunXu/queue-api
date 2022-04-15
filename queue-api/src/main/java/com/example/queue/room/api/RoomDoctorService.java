package com.example.queue.room.api;

import com.example.queue.room.api.bo.RoomDoctor;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface RoomDoctorService {

    /**
     *
     * @param roomId
     * @param roomDoctor
     * @return
     */
    int countRoomDoctor(String roomId, RoomDoctor roomDoctor);

    /**
     * 
     * @param roomId
     * @param roomDoctor
     * @return
     */
    List<RoomDoctor> listRoomDoctors(String roomId, RoomDoctor roomDoctor);

    /**
     * 
     * @param roomId
     * @param doctorId
     * @param creator
     * @return
     */
    RoomDoctor insertRoomDoctor(BigInteger roomId, BigInteger doctorId, String creator);

}
