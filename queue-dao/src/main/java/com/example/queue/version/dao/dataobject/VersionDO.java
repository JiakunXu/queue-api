package com.example.queue.version.dao.dataobject;

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
public class VersionDO extends BaseDO {

    private static final long serialVersionUID = -7685611739723386779L;

    private BigInteger        id;

    private BigInteger        appId;

    /**
     * android
     * wgt
     */
    private String            client;

    /**
     * 更新地址.
     */
    private String            url;

}
