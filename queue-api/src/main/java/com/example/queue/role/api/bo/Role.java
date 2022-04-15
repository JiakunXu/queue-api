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
public class Role extends BaseBO {

    private static final long serialVersionUID = -1478226712473288947L;

    private BigInteger        id;

    private String            code;

    private String            name;

}
