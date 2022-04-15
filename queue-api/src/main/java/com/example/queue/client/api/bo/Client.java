package com.example.queue.client.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Client extends BaseBO {

    private static final long serialVersionUID = 9061420268087358744L;

    private BigInteger        id;

    private String            code;

    private String            name;

    private String            ip;

    /**
     * area：区域；
     * dept：科室；
     * room：诊室；
     * media：无
     */
    private String            scene;

    private BigInteger        sceneId;

    /**
     * 未使用；
     * 使用中
     */
    private String            status;

}
