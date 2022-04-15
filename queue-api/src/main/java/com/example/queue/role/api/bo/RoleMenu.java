package com.example.queue.role.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class RoleMenu extends BaseBO {

    private static final long serialVersionUID = -5917180184392604175L;

    private BigInteger        id;

    private BigInteger        roleId;

    private BigInteger        menuId;

}
