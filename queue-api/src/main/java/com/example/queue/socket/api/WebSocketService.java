package com.example.queue.socket.api;

/**
 * @author JiakunXu
 */
public interface WebSocketService {

    /**
     *
     * @param tunnelId
     * @param message
     */
    void sendMessage(String tunnelId, String message);

}
