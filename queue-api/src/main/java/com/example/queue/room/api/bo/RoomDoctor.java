package com.example.queue.room.api.bo;

import com.example.queue.doctor.api.bo.Doctor;
import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class RoomDoctor extends BaseBO {

    private static final long serialVersionUID = 6235824512150122551L;

    private BigInteger        id;

    private BigInteger        roomId;

    private BigInteger        doctorId;

    private Doctor            doctor;

}
