package com.SCC.environment.model;

import java.io.Serializable;

/**
 * Created by ZJDX on 2016/6/20.
 */

public class Path implements Serializable {
    private Coordinate startPoint;
    private Coordinate endPoint;

    public Coordinate getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Coordinate endPoint) {
        this.endPoint = endPoint;
    }

    public Coordinate getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Coordinate startPoint) {
        this.startPoint = startPoint;
    }






}
