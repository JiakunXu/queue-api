package com.example.queue.client.dao.dataobject;

import com.example.queue.framework.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
@ToString
public class ClientDO extends BaseDO {

    private static final long serialVersionUID = 6510567170276854389L;

    private BigInteger        id;

    private String            code;

    private String            name;

    private String            ip;

    /**
     * area：区域
     * dept：科室
     * room：诊室
     * media：无
     */
    private String            scene;

    private BigInteger        sceneId;

    private String            status;

}
