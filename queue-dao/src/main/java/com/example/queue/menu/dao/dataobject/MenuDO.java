package com.example.queue.menu.dao.dataobject;

import com.example.queue.framework.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class MenuDO extends BaseDO {

    private static final long serialVersionUID = 6660528679345819510L;

    private BigInteger        id;

    private BigInteger        pid;

    private String            name;

}
