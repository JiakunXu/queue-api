package com.example.queue.version.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Version extends BaseBO {

    private static final long serialVersionUID = -6457811701667429164L;

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
