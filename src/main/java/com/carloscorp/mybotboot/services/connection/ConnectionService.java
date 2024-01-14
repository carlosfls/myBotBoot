package com.carloscorp.mybotboot.services.connection;

import com.carloscorp.mybotboot.config.AppStompSessionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Slf4j
public class ConnectionService {

    private static WebSocketStompClient stompClient;
    private static StompSession session;
    private static final String SERVER = "localhost:8031";

    private ConnectionService() {
    }

    /**
     * Connect to the ws server
     */
    public static void connect() {
        WebSocketClient client = new StandardWebSocketClient();

        stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        startConnection();
    }

    /**
     * Start the connection with the server and retry if fail
     */
    public static void startConnection() {
        String uri = "ws://" + SERVER + "/api/websocket";
        // Add custom headers to WebSocketStompClient requests
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.add("X-AppId-Header", "1");
        headers.add("X-Worker-Status", "ONLINE");
        // Init AppStompSessionHandler
        StompSessionHandler sessionHandler = new AppStompSessionHandler();
        while (true) {
            try {
                log.info(uri);
                session = stompClient.connectAsync(uri, headers, sessionHandler).get();
                log.info("Connected to the server");
                if (isRunning()) {
                    break;
                }
            } catch (Exception e) {
                log.error("Connection error " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Check if running the connection ws
     */
    public static boolean isRunning() {
        if (session != null)
            return session.isConnected();
        return false;
    }

    /**
     * UnRegister app when exit for avoid errors
     */
    public static void unRegister() {
        try {
            session.disconnect();
            stompClient.stop();
        } catch (Exception ex) {
            log.error("Error closing the ws " + ex);
            Thread.currentThread().interrupt();
        }
    }
}
