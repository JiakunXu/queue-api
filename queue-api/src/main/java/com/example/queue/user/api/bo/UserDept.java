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
public class UserDept extends BaseBO {

    private static final long serialVersionUID = 1943862079223375667L;

    private BigInteger        id;

    private BigInteger        userId;

    private BigInteger        deptId;

    private User              user;

}
