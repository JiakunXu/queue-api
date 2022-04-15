package com.example.queue.media.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Media extends BaseBO {

    private static final long serialVersionUID = -616131733103140703L;

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
