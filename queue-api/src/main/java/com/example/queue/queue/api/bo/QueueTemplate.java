package com.example.queue.queue.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class QueueTemplate extends BaseBO {

    private static final long serialVersionUID = 3736642170353246894L;

    private BigInteger        id;

    private BigInteger        queueId;

    /**
     * 模版.
     */
    private BigInteger        templateId;

}
