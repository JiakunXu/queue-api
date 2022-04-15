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
public class DictDO extends BaseDO {

    private static final long serialVersionUID = -1554459065034110865L;

    private BigInteger        id;

    private BigInteger        dictTypeId;

    private String            dictTypeValue;

    private String            name;

    private String            value;

    private String            remark;

}
