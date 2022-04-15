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
public class RoleMenuDO extends BaseDO {

    private static final long serialVersionUID = -2990778737001783707L;

    private BigInteger        id;

    private BigInteger        roleId;

    private BigInteger        menuId;

}
