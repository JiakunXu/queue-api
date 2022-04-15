package com.example.queue.role.dao.dataobject;

import com.example.queue.framework.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class RoleDO extends BaseDO {

    private static final long serialVersionUID = -4303390004422966280L;

    private BigInteger        id;

    private String            code;

    private String            name;

}
