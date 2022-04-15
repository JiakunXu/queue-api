package com.example.queue.notice.dao.dataobject;

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
public class NoticeDO extends BaseDO {

    private static final long serialVersionUID = 8066950797290066648L;

    private BigInteger        id;

    /**
     * 科室.
     */
    private BigInteger        deptId;

    /**
     * 开始时间.
     */
    private Date              startTime;

    /**
     * 结束时间.
     */
    private Date              endTime;

    /**
     * 内容.
     */
    private String            content;

    private String            source;

}
