package com.example.queue.queue.api.bo;

import com.example.queue.framework.bo.BaseBO;
import com.example.queue.rule.api.bo.Rule;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class QueueRule extends BaseBO {

    private static final long serialVersionUID = 6570766703446736975L;

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

    private Rule              rule;

}
