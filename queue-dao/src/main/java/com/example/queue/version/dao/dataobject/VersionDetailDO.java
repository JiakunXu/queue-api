package com.example.queue.version.dao.dataobject;

import com.example.queue.framework.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class VersionDetailDO extends BaseDO {

    private static final long serialVersionUID = -3023229128606897560L;

    private BigInteger        id;

    private BigInteger        versionId;

    /**
     * 1.0.0.
     */
    private String            version;

    private String            status;

}
