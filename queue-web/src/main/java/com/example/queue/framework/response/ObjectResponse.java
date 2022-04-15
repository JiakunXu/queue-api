package com.example.queue.framework.response;

import com.example.queue.framework.constant.Constants;
import lombok.Getter;
import lombok.Setter;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class ObjectResponse<T> extends AbstractResponse {

    private static final long serialVersionUID = 5661972515266196297L;

    private T                 object;

    public ObjectResponse(T object) {
        this.setCode(Constants.SUCCESS);
        this.setObject(object);
    }

}
