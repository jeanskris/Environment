package com.SCC.environment.controller;

import com.SCC.environment.model.Coordinate;
import com.SCC.environment.sensor.Map;
import com.SCC.environment.sensor.PerceptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ZJDX on 2016/6/25.
 */
@RestController
public class Action {
    private static Logger logger = LoggerFactory.getLogger(Action.class);

    @Autowired
    PerceptService perceptService;

    @RequestMapping(value = "/getMap", method = RequestMethod.GET)
    public Map getMap(@RequestParam(value="mapId", required=false) Integer id) {
        System.out.println("mapId:" + id);
        Map map=perceptService.getMapByOpencv();
        return map;
    }

    @RequestMapping(value = "/mouseClickPoint", method = RequestMethod.POST)
    public void mouseClickPoint(@RequestBody Coordinate coordinate) {
        System.out.println("coordinate:" + coordinate.getX());
    }
}
