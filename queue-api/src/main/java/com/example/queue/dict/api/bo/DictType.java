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
public class DictType extends BaseBO {

    private static final long serialVersionUID = 8287406623074339976L;

    private BigInteger        id;

    private String            name;

    private String            value;

    private String            remark;

}
