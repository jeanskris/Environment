package com.SCC.environment.mina;

/**
 * Created by ZJDX on 2016/6/25.
 */
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * @Description: 单例工具类，保存所有mina客户端连接
 * @author whl
 * @date 2014-9-29 上午10:09:15
 *
 */
public class SessionMap {

    private final static Logger log = LoggerFactory.getLogger(SessionMap.class);

    private static SessionMap sessionMap = null;

    private Map<String, IoSession>map = new HashMap<String, IoSession>();


    //构造私有化 单例
    private SessionMap(){}


    /**
     * @Description: 获取唯一实例
     */
    public static SessionMap newInstance(){
        log.debug("SessionMap单例获取---");
        if(sessionMap == null){
            sessionMap = new SessionMap();
        }
        return sessionMap;
    }


    /**
     * @Description: 保存session会话
     */
    public void addSession(String key, IoSession session){
        log.debug("保存会话到SessionMap单例---key=" + key);
        this.map.put(key, session);
    }

    /**
     * @Description: 根据key查找缓存的session
     */
    public IoSession getSession(String key){
        log.debug("获取会话从SessionMap单例---key=" + key);
        return this.map.get(key);
    }

    /**
     * @Description: 发送消息到客户端
     */
    public void sendMessage(String[] keys, Object message){
        for(String key : keys){
            IoSession session = getSession(key);

            log.debug("反向发送消息到客户端Session---key=" + key + "----------消息=" + message);
            if(session == null){
                return;
            }
            session.write(message);

        }
    }


    /**
     * @Description: 发送消息到客户端
     */
    public void send(String key, Object message){
            IoSession session = getSession(key);
            log.debug("反向发送消息到客户端Session---key=" + key + "----------消息=" + message);
            if(session == null){
                return;
            }
            session.write(message);

    }


}
