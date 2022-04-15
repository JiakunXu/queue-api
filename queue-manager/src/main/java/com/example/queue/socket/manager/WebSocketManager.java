package com.example.queue.socket.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JiakunXu
 */
public class WebSocketManager {

    private static final Logger                        logger  = LoggerFactory
        .getLogger(WebSocketManager.class);

    private static final Map<String, WebSocketSession> SESSION = new ConcurrentHashMap<>();

    /**
     *
     * @param key
     * @return
     */
    public static WebSocketSession get(String key) {
        return SESSION.get(key);
    }

    /**
     *
     * @param key
     * @param session
     * @return
     */
    public static WebSocketSession put(String key, WebSocketSession session) {
        return SESSION.put(key, session);
    }

    /**
     *
     * @param key
     * @return
     */
    public static WebSocketSession remove(String key) {
        WebSocketSession session = SESSION.remove(key);

        if (session != null && session.isOpen()) {
            try {
                session.close();
            } catch (IOException e) {
                logger.error("remove", e);
            }
        }

        return session;
    }

}
