package com.SCC.environment.websocket;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by ZJDX on 2016/7/8.
 */
@RequestMapping("websocket")
public class WebsocketEndPoint extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String phonemessage = message.getPayload();

        session.sendMessage(message);
    }
    @Override
    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);

        System.out.println("Connection Establied!");

    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Connection Closed！");
    }

}