package com.example.queue.config.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Voice extends BaseBO {

    private static final long serialVersionUID = -3298280038022341259L;

    private BigInteger        id;

    private String            code;

    private String            name;

    /**
     * {}.
     */
    private String            content;

}
