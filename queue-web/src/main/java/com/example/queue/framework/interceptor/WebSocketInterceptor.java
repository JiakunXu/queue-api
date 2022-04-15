package com.example.queue.framework.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author JiakunXu
 */
@Component
public class WebSocketInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            HttpServletRequest servletRequest = serverRequest.getServletRequest();
            Enumeration<String> names = servletRequest.getParameterNames();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                attributes.put(name, servletRequest.getParameter(name));
            }
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, @Nullable Exception exception) {
        if (request instanceof ServletServerHttpRequest
            && response instanceof ServletServerHttpResponse) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            String name = "Sec-WebSocket-Protocol";
            String header = serverRequest.getServletRequest().getHeader(name);
            if (StringUtils.isNotBlank(header)) {
                ServletServerHttpResponse serverResponse = (ServletServerHttpResponse) response;
                serverResponse.getServletResponse().addHeader(name, header);
            }
        }
    }

}
