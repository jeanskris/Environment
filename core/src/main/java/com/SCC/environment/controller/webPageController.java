package com.SCC.environment.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZJDX on 2016/7/6.
 */
public class webPageController {
    /**
     * index
     * when client request"/index.html",server return index.html
     * @return no
     */
    @CrossOrigin
    @RequestMapping(value = "/index.html")
    public ModelAndView index(){
        System.out.println("/index");
        ModelAndView mv = new ModelAndView("index");

        return mv;
    }
}
