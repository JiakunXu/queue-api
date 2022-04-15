package com.example.queue.queue.api.bo;

import com.example.queue.dept.api.bo.Dept;
import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Queue extends BaseBO {

    private static final long serialVersionUID = 1925968393579834005L;

    private BigInteger        id;

    /**
     * 科室.
     */
    private BigInteger        deptId;

    /**
     * 已签到号.
     */
    private Integer           signedNo;

    private String            status;

    private Dept              dept;

}
