package com.example.queue.user.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class User extends BaseBO {

    private static final long serialVersionUID = -1671144567868739418L;

    private BigInteger        id;

    private String            name;

    /**
     * 角色.
     */
    private BigInteger        roleId;

    private String            status;

}
