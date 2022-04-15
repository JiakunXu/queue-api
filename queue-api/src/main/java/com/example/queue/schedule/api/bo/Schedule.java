package com.example.queue.schedule.api.bo;

import com.example.queue.doctor.api.bo.Doctor;
import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Schedule extends BaseBO {

    private static final long serialVersionUID = -3843832471642147468L;

    private BigInteger        id;

    /**
     * 科室.
     */
    private BigInteger        deptId;

    private String            code;

    /**
     * yyyy-mm-dd.
     */
    private Date              date;

    /**
     * am
     * pm
     */
    private String            time;

    /**
     * 医生.
     */
    private BigInteger        doctorId;

    private Integer           maxNo;

    private Doctor            doctor;

}
