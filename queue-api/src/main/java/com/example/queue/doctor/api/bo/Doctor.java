package com.example.queue.doctor.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Doctor extends BaseBO {

    private static final long serialVersionUID = 703169599556428421L;

    private BigInteger        id;

    private BigInteger        userId;

    private String            code;

    private String            name;

    private String            headImgUrl;

    /**
     * 职称.
     */
    private String            title;

    /**
     * 简介.
     */
    private String            content;

}
