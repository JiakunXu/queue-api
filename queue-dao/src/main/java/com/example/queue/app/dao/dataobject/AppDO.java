package com.example.queue.app.dao.dataobject;

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
public class AppDO extends BaseDO {

    private static final long serialVersionUID = 4613699998713649981L;

    private BigInteger        id;

    private String            name;

}
