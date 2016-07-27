package com.SCC.environment.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by ZJDX on 2016/7/8.
 */
public class MyHandler extends TextWebSocketHandler {


    public void handlerTextMessage(WebSocketSession session, TextMessage message) {
        //...
    }

}