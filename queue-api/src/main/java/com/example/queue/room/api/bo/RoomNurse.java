package com.example.queue.room.api.bo;

import com.example.queue.framework.bo.BaseBO;
import com.example.queue.nurse.api.bo.Nurse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class RoomNurse extends BaseBO {

    private static final long serialVersionUID = 8978989897676179921L;

    private BigInteger        id;

    private BigInteger        roomId;

    private BigInteger        nurseId;

    private Nurse             nurse;

}
