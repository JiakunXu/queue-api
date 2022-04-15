package com.example.queue.rule.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Rule extends BaseBO {

    private static final long serialVersionUID = -7736930389637434376L;

    private BigInteger        id;

    private String            code;

    /**
     * 说明.
     */
    private String            content;

}
