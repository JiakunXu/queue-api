package com.example.queue.framework.response;

/**
 * @author JiakunXu
 */
public class ExceptionResponse extends AbstractResponse {

    private static final long serialVersionUID = 5873823141210593582L;

    public ExceptionResponse(String code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }

}
