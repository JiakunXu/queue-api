package com.example.queue.media.dao.dataobject;

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
public class MediaDO extends BaseDO {

    private static final long serialVersionUID = 1318052577810996673L;

    private BigInteger        id;

    private String            code;

    private String            name;

    /**
     * video
     * image
     */
    private String            type;

    private String            fileList;

}
