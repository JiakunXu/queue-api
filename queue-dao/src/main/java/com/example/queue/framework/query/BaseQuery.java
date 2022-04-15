package com.example.queue.framework.query;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class BaseQuery implements Serializable {

    private static final long serialVersionUID = -2047538090603282418L;

    private String            search;

    private String            startDate;

    private String            endDate;

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
    @JSONField(serialize = false)
    private int               pageNo;

    @JSONField(serialize = false)
    private int               pageSize;

    @JSONField(serialize = false)
    private int               offset;

    public int getOffset() {
        return (this.pageNo - 1) * this.pageSize;
    }

}
