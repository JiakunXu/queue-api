package com.example.queue.app.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class App extends BaseBO {

    private static final long serialVersionUID = -3303297578691092555L;

    private BigInteger        id;

    private String            name;

}
