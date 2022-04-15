package com.example.queue.framework.config;

import com.example.queue.framework.handler.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * @author JiakunXu
 */
@Configuration
@EnableWebSocket
public class SocketConfigurer implements WebSocketConfigurer {

    @Autowired
    private HandshakeInterceptor webSocketInterceptor;

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(webSocketHandler, "/ws")
            .addInterceptors(webSocketInterceptor).setAllowedOrigins("*");
    }

}
