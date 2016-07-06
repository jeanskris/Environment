package com.SCC.environment.mina;


import com.SCC.environment.model.Constant;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component("tcpServerHandler")
public class TCPServerHandler extends IoHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(TCPServerHandler.class);


    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("data from client:"+message);
    /*    String clientIP = (String)session.getAttribute("KEY_SESSION_CLIENT_IP");
        logger.debug(" messageReceived,client IP: " + clientIP);
        System.out.println("messageReceived,client IP:"+clientIP);*/
        if (message instanceof IoBuffer) {
            IoBuffer buffer = (IoBuffer) message;
            System.out.println(buffer.toString());
            buffer.setAutoShrink(true);
            buffer.setAutoExpand(true);
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);
           // carService.getDataFromCar(bytes); //调用service业务处理
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        System.out.println("data has been sent to client");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        InetSocketAddress address =(InetSocketAddress)session.getRemoteAddress();
      //  String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
        Constant.TCP_REMOTE_SERVER_IP=address.getAddress().toString().substring(1);
        //Constant.TCP_REMOTE_SERVER_PORT=address.getPort();
        logger.debug("sessionCreated, TCP_REMOTE_SERVER_IP: " +   Constant.TCP_REMOTE_SERVER_IP);
        System.out.println("sessionCreated,TCP_REMOTE_SERVER_IP: " +   Constant.TCP_REMOTE_SERVER_IP);
        //保存客户端的会话session
        SessionMap sessionMap = SessionMap.newInstance();
        sessionMap.addSession(Constant.TCP_REMOTE_SERVER_IP, session);
    }
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("Server sessionClosed" );
    }

    @Override
    public void sessionIdle(IoSession iosession, IdleStatus idlestatus)
            throws Exception {
        System.out.println("sessionIdle");
        super.sessionIdle(iosession, idlestatus);
    }
    @Override
    public void sessionOpened(IoSession iosession) throws Exception {
        System.out.println("sessionOpened");
        super.sessionOpened(iosession);
    }
}
