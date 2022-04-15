package com.example.queue.area.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Area extends BaseBO {

    private static final long serialVersionUID = -5423015819771384452L;

    private BigInteger        id;

    private String            code;

    private String            name;

    private String            location;

}
