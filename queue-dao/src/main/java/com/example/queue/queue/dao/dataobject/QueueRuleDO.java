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
public class QueueRuleDO extends BaseDO {

    private static final long serialVersionUID = -6990750620284408933L;

    private BigInteger        id;

    private BigInteger        queueId;

    /**
     * 规则.
     */
    private BigInteger        ruleId;

    private String            ruleCode;

    /**
     * 阈值（上限）.
     */
    private Integer           value;

}
