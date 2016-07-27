package com.SCC.environment.model;

import java.io.Serializable;

/**
 * Created by ZJDX on 2016/6/20.
 */
public class PointInfo implements Serializable {

    private Coordinate coordinate;
    private int speed;
    private int angle;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }


}
