package com.SCC.environment.mina;


import com.SCC.environment.model.Constant;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.InetSocketAddress;

@Component("tcpAddress")
public class ServerIpTCP extends InetSocketAddress {
    /*public ServerIpTCP() {
        super("114.215.144.107", 5555);
    }//aliyun*/
    public ServerIpTCP() {
        super(Constant.TCP_SERVER_IP, Constant.TCP_SERVER_PORT);
    } //localhost
    public ServerIpTCP(InetAddress add, int port) {
        super(add, port);
    }
}
