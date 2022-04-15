package com.example.queue.nurse.dao.dataobject;

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
public class NurseDO extends BaseDO {

    private static final long serialVersionUID = 4542357574396989217L;

    private BigInteger        id;

    private BigInteger        userId;

    private String            code;

    private String            name;

}
