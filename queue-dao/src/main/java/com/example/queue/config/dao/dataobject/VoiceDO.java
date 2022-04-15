package com.example.queue.config.dao.dataobject;

import com.example.queue.framework.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class VoiceDO extends BaseDO {

    private static final long serialVersionUID = -586242071459462229L;

    private BigInteger        id;

    private String            code;

    private String            name;

    /**
     * {}.
     */
    private String            content;

}
