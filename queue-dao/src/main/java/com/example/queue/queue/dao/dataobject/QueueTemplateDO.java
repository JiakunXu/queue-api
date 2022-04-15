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
public class QueueTemplateDO extends BaseDO {

    private static final long serialVersionUID = -4474787946385795283L;

    private BigInteger        id;

    private BigInteger        queueId;

    /**
     * 模版.
     */
    private BigInteger        templateId;

}
