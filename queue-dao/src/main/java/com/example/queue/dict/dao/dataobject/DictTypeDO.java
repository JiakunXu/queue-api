package com.example.queue.dict.dao.dataobject;

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
public class DictTypeDO extends BaseDO {

    private static final long serialVersionUID = -690143118240010668L;

    private BigInteger        id;

    private String            name;

    private String            value;

    private String            remark;

}
