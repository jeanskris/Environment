package com.SCC.environment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZJDX on 2016/7/6.
 */
@CrossOrigin
@Controller
public class webPageController {
    private static Logger logger = LoggerFactory.getLogger(webPageController.class);
    /**
     * index
     * when client request"/index.html",server return index.html
     * @return no
     */

    @RequestMapping(value = "/index.html")
    public ModelAndView index(){
        System.out.println("/index");
        ModelAndView mv = new ModelAndView("index");

        return mv;
    }
    @MessageMapping("/location")
    @SendTo("/topic/getLocation")
    public String getLocation(String value){
        logger.debug("getLocation");
        System.out.println("value:"+value);
        return value;
    }
    @MessageExceptionHandler
    @SendToUser(destinations="/queue/errors", broadcast=false)
    public String  handleException(Exception exception) {
        return exception.toString();
    }


}
