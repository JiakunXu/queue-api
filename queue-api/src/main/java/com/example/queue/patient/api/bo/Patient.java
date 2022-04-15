package com.example.queue.patient.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Patient extends BaseBO {

    private static final long serialVersionUID = 3315410657660495241L;

    private BigInteger        id;

    private String            code;

    private String            name;

    private BigInteger        memberId;

}
