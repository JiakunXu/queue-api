package com.example.queue.dept.dao.dataobject;

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
public class DeptDO extends BaseDO {

    private static final long serialVersionUID = -8302972150301758279L;

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
