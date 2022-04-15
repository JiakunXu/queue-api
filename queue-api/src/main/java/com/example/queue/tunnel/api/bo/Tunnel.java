package com.example.queue.tunnel.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Tunnel extends BaseBO {

    private static final long serialVersionUID = -2952991612958654453L;

    private BigInteger        id;

    private BigInteger        clientId;

    private String            tunnelId;

}
