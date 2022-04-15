package com.example.queue.framework.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public abstract class AbstractResponse implements Serializable {

    private static final long   serialVersionUID = -2112675577113436644L;

    private String              code;

    private String              msg;

    private String              subCode;

    private String              subMsg;

    private String              body;

    private Map<String, String> params;

}
