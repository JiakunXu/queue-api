package com.example.queue.member.api.bo;

import com.example.queue.framework.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Getter
@Setter
public class Member extends BaseBO {

    private static final long serialVersionUID = 7319113976940844042L;

    private BigInteger        id;

    /**
     * 用户的唯一标识.
     */
    private String            openid;

    /**
     * 用户昵称.
     */
    private String            nickname;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知.
     */
    private Integer           sex;

    /**
     * 用户个人资料填写的省份.
     */
    private String            province;

    /**
     * 普通用户个人资料填写的城市.
     */
    private String            city;

    /**
     * 国家，如中国为CN.
     */
    private String            country;

    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。.
     */
    private String            headimgurl;

    private String            tel;

}
