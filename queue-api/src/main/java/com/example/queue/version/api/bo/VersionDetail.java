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
public class VersionDetail extends BaseBO {

    private static final long serialVersionUID = 2021072903178216331L;

    private BigInteger        id;

    private BigInteger        versionId;

    /**
     * 1.0.0.
     */
    private String            version;

    private String            status;

}
