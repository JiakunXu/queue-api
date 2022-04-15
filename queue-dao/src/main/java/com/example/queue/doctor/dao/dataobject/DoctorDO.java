package com.example.queue.doctor.dao.dataobject;

import com.example.queue.framework.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class DoctorDO extends BaseDO {

    private static final long serialVersionUID = -4136030204616147385L;

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
