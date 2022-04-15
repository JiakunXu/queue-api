package com.example.queue.config.dao.dataobject;

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
public class StyleDO extends BaseDO {

    private static final long serialVersionUID = -1598533749012275418L;

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
