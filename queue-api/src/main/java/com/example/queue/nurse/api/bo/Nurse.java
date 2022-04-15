package com.example.queue.nurse.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Nurse extends BaseBO {

    private static final long serialVersionUID = 5324698534339499238L;

    private BigInteger        id;

    private BigInteger        userId;

    private String            code;

    private String            name;

}
