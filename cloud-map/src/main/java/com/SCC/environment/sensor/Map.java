package com.SCC.environment.sensor;

import com.SCC.environment.model.Coordinate;

import java.util.List;

/**
 * Created by ZJDX on 2016/7/4.
 */
public class Map {
      public List<Coordinate> getPoints() {
            return points;
      }

      public void setPoints(List<Coordinate> points) {
            this.points = points;
      }

      List<Coordinate> points;

      public double getMappingX() {
            return mappingX;
      }

      public void setMappingX(double mappingX) {
            this.mappingX = mappingX;
      }

      public double getMappingY() {
            return mappingY;
      }

      public void setMappingY(double mappingY) {
            this.mappingY = mappingY;
      }



      double mappingX;//实际地图与存储地图的x差值,用于接收定位点时进行相同的映射处理。
      double mappingY;

}
