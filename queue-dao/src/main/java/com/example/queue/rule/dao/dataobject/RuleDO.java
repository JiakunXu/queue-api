package com.example.queue.rule.dao.dataobject;

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
public class RuleDO extends BaseDO {

    private static final long serialVersionUID = -5815966335955377753L;

    private BigInteger        id;

    private String            code;

    /**
     * 说明.
     */
    private String            content;

}
