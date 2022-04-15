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
public class PageResponse<T> extends AbstractResponse {

    private static final long serialVersionUID = 5272336433321751345L;

    private Integer           pageNo;

    private Integer           pageSize;

    private Integer           pageCount;

    private Integer           totalCount;

    private List<T>           list;

    public PageResponse(Integer pageNo, Integer pageSize, Integer totalCount, List<T> list) {
        this.setCode(Constants.SUCCESS);
        this.setPageNo(pageNo == null ? 0 : pageNo);
        this.setPageSize(pageSize == null ? 0 : pageSize);
        this.setTotalCount(totalCount == null ? 0 : totalCount);
        this.setList(list);

        if (this.pageSize == 0 || this.totalCount == 0) {
            this.setPageCount(1);
        } else {
            this.setPageCount(
                this.totalCount / this.pageSize + (this.totalCount % this.pageSize == 0 ? 0 : 1));
        }
    }

}
