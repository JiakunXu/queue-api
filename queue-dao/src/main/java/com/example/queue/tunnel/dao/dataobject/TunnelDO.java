package com.example.queue.tunnel.dao.dataobject;

import com.example.queue.framework.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class TunnelDO extends BaseDO {

    private static final long serialVersionUID = -7658791544976004141L;

    private BigInteger        id;

    private BigInteger        clientId;

    private String            tunnelId;

}
