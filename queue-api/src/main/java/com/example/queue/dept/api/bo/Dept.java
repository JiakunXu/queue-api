package com.example.queue.dept.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Dept extends BaseBO {

    private static final long serialVersionUID = -9217529478915793056L;

    private BigInteger        id;

    /**
     * 上级.
     */
    private BigInteger        pid;

    private String            code;

    private String            name;

    /**
     * 护士站 - 样式.
     */
    private BigInteger        deptStyleId;

    /**
     * 护士站 - 语音.
     */
    private BigInteger        deptVoiceId;

    /**
     * 诊室 - 样式.
     */
    private BigInteger        roomStyleId;

}
