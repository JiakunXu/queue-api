package com.example.queue.queue.dao.dataobject;

import com.example.queue.framework.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class QueueDetailDO extends BaseDO {

    private static final long serialVersionUID = 6123112771571307381L;

    private BigInteger        id;

    private BigInteger        queueId;

    /**
     * 诊室.
     */
    private BigInteger        roomId;

    /**
     * 患者.
     */
    private BigInteger        patientId;

    private String            patientName;

    private Integer           no;

    /**
     * 状态.
     */
    private String            status;

}
