package com.example.queue.framework.response;

import com.example.queue.framework.constant.Constants;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class ListResponse<T> extends AbstractResponse {

    private static final long serialVersionUID = -5567902033588518919L;

    private Integer           total;

    private List<T>           list;

    public ListResponse(int total, List<T> list) {
        this.setCode(Constants.SUCCESS);
        this.setTotal(total);
        this.setList(list);
    }

    public ListResponse(List<T> list) {
        this.setCode(Constants.SUCCESS);
        this.setList(list);
    }

}
