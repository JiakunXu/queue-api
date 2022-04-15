package com.example.queue.room.dao.dataobject;

import com.example.queue.framework.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
@ToString
public class RoomDoctorDO extends BaseDO {

    private static final long serialVersionUID = 3129238310530417051L;

    private BigInteger        id;

    private BigInteger        roomId;

    private BigInteger        doctorId;

}
