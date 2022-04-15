package com.example.queue.patient.dao.dataobject;

import com.example.queue.framework.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class PatientDO extends BaseDO {

    private static final long serialVersionUID = -8442177618730443088L;

    private BigInteger        id;

    private String            code;

    private String            name;

    private BigInteger        memberId;

}
