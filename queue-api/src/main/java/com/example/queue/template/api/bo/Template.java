package com.example.queue.template.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Template extends BaseBO {

    private static final long serialVersionUID = 3259726458203634790L;

    private BigInteger        id;

    private String            templateId;

    private String            name;

    private String            content;

}
