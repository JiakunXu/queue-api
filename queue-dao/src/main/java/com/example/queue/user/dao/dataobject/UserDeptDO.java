package com.example.queue.user.dao.dataobject;

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
public class UserDeptDO extends BaseDO {

    private static final long serialVersionUID = 6687764318104624618L;

    private BigInteger        id;

    private BigInteger        userId;

    private BigInteger        deptId;

}
