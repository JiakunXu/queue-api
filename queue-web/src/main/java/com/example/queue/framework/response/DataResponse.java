package com.example.queue.framework.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class DataResponse<T> implements Serializable {

    private static final long serialVersionUID = 1707290933698340722L;

    private int               code;

    private T                 data;

    private String            error;

    private String            message;

}