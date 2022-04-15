package com.example.queue.queue.dao.dataobject;

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
public class QueueDO extends BaseDO {

    private static final long serialVersionUID = 3743164730012524326L;

    private BigInteger        id;

    /**
     * 科室.
     */
    private BigInteger        deptId;

    /**
     * 已签到号.
     */
    private Integer           signedNo;

    private String            status;

}
