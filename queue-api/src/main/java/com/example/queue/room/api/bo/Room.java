package com.example.queue.room.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Room extends BaseBO {

    private static final long serialVersionUID = 6982387503926685763L;

    private BigInteger        id;

    /**
     * 科室.
     */
    private BigInteger        deptId;

    private String            code;

    private String            name;

    private String            ip;

    /**
     * 未使用；
     * 使用中；
     * 暂停中
     */
    private String            status;

}
