package com.example.queue.user.dao.dataobject;

import com.example.queue.framework.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class UserDO extends BaseDO {

    private static final long serialVersionUID = -4621578414872067135L;

    private BigInteger        id;

    private String            name;

    /**
     * 账号.
     */
    private String            passport;

    /**
     * 密码.
     */
    private String            password;

    /**
     * 角色.
     */
    private BigInteger        roleId;

    private String            status;

}
