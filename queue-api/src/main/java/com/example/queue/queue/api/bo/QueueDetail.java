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
public class QueueDetail extends BaseBO {

    private static final long serialVersionUID = 7065794529928945350L;

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
     * 已签到 未叫号 未就诊；
     * 已签到 已叫号 未就诊；
     * 已签到 已叫号 已就诊；
     * 已签到 已过号 未就诊
     */
    private String            status;

}
