package com.example.queue.socket.manager;

import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.socket.api.WebSocketService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * @author JiakunXu
 */
@Service
public class WebSocketServiceImpl implements WebSocketService {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServiceImpl.class);

    @Override
    public void sendMessage(String tunnelId, String message) {
        if (StringUtils.isBlank(tunnelId) || StringUtils.isBlank(message)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        WebSocketSession session = WebSocketManager.get(tunnelId);

        if (session == null) {
            return;
        }

        if (session.isOpen()) {
            try {
                session.sendMessage(new TextMessage("message:" + message));
            } catch (IOException e) {
                logger.error("sendMessage", e);
            }
            return;
        }
    }

}
