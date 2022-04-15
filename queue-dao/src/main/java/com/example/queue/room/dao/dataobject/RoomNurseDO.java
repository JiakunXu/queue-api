package com.example.queue.room.dao.dataobject;

import com.example.queue.framework.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class RoomNurseDO extends BaseDO {

    private static final long serialVersionUID = 4498784443996294901L;

    private BigInteger        id;

    private BigInteger        roomId;

    private BigInteger        nurseId;

}
