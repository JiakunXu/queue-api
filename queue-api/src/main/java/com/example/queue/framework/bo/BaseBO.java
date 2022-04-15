package com.example.queue.framework.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class BaseBO implements Serializable {

    private static final long serialVersionUID = 6425935261415820282L;

    private String            search;

    private String            startDate;

    private String            endDate;

    /**
     * @see com.example.queue.framework.config.JsonConfigurer
     */
    private String[]          codes;

    /**
     * 排序字段.
     */
    private String            sort;

    /**
     * 排序类型.
     */
    private String            dir;

    /**
     * 分页号，从1开始.
     */
    private Integer           pageNo;

    private Integer           pageSize;

}
