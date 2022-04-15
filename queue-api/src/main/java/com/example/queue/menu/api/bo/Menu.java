package com.example.queue.menu.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Menu extends BaseBO {

    private static final long serialVersionUID = 8037514920724837652L;

    private BigInteger        id;

    private BigInteger        pid;

    private String            name;

}
