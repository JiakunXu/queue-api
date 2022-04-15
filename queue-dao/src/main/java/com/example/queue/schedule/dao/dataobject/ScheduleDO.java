package com.example.queue.schedule.dao.dataobject;

import com.example.queue.framework.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author JiakunXu
 */
@Getter
@Setter
@ToString
public class ScheduleDO extends BaseDO {

    private static final long serialVersionUID = -1367109189989916379L;

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

}
