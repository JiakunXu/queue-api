package com.example.queue.socket.api.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 消息体，会使用 JSON 序列化后作为 packet.content.
 *
 * @author JiakunXu
 *
 */
@Getter
@Setter
public class Message implements Serializable {

    private static final long serialVersionUID = 7729049438247636909L;

    /**
     * 消息类型，表示业务消息类型.
     */
    private String            type;

    /**
     * 消息实体，可以为任意类型，表示消息的附带数据，也可以为空.
     */
    private String            content;

    public Message(String type, String content) {
        this.type = type;
        this.content = content;
    }

}
