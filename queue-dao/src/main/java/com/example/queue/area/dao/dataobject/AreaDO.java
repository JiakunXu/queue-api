package com.example.queue.area.dao.dataobject;

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
public class AreaDO extends BaseDO {

    private static final long serialVersionUID = -1357780235080156691L;

    private BigInteger        id;

    private String            code;

    private String            name;

    private String            location;

}
