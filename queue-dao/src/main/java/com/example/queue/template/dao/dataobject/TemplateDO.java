package com.example.queue.template.dao.dataobject;

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
public class TemplateDO extends BaseDO {

    private static final long serialVersionUID = -4066173977268196883L;

    private BigInteger        id;

    private String            templateId;

    private String            name;

    private String            content;

}
