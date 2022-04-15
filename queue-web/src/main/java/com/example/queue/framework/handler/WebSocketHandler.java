package com.example.queue.framework.handler;

import com.example.queue.cache.api.RedisService;
import com.example.queue.client.api.bo.Client;
import com.example.queue.socket.manager.WebSocketManager;
import com.example.queue.tunnel.api.TunnelService;
import com.example.queue.tunnel.api.bo.Tunnel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;

/**
 * @author JiakunXu
 */
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger          logger = LoggerFactory.getLogger(WebSocketHandler.class);

    @Autowired
    private RedisService<String, Client> redisService;

    @Autowired
    private TunnelService                tunnelService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String, Object> attributes = session.getAttributes();

        Object token = attributes.get("token");

        if (token == null) {
            session.close(CloseStatus.BAD_DATA);
            return;
        }

        Client client = getClient((String) token);

        if (client == null) {
            session.close(CloseStatus.BAD_DATA);
            return;
        }

        Tunnel tunnel = tunnelService.insertTunnel(client.getId(), "sys");

        session.getAttributes().put("tunnelId", tunnel.getTunnelId());

        WebSocketManager.put(tunnel.getTunnelId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        if ("ping".equals(message.getPayload())) {
            session.sendMessage(new TextMessage("pong"));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session,
                                     Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus status) throws Exception {
        if (!CloseStatus.BAD_DATA.equals(status)) {
            String tunnelId = (String) session.getAttributes().get("tunnelId");

            WebSocketManager.remove(tunnelId);
        }
    }

    /**
     * 
     * @param token
     * @return
     */
    private Client getClient(String token) {
        try {
            return redisService.get(token);
        } catch (Exception e) {
            logger.error("getClient", e);
        }

        return null;
    }

}
