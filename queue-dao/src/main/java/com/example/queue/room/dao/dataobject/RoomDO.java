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
public class RoomDO extends BaseDO {

    private static final long serialVersionUID = -6105449159070685329L;

    private BigInteger        id;

    /**
     * 科室.
     */
    private BigInteger        deptId;

    private String            code;

    private String            name;

    private String            ip;

    /**
     * 状态.
     */
    private String            status;

}
