package com.example.queue.dict.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Dict extends BaseBO {

    private static final long serialVersionUID = 3444091435399505776L;

    private BigInteger        id;

    private BigInteger        dictTypeId;

    private String            dictTypeValue;

    private String            name;

    private String            value;

    private String            remark;

}
