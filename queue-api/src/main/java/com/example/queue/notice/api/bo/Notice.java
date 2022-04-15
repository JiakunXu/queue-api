package com.example.queue.notice.api.bo;

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
public class Notice extends BaseBO {

    private static final long serialVersionUID = 5649355356983373185L;

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
