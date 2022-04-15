package com.example.queue.config.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Style extends BaseBO {

    private static final long serialVersionUID = 5762975579911067582L;

    private BigInteger        id;

    private String            code;

    private String            name;

    /**
     * 示例.
     */
    private String            url;

    /**
     * {}.
     */
    private String            content;

}
