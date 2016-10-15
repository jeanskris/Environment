package com.SCC.environment.sensor;

import com.SCC.environment.dao.RedisDao;
import com.SCC.environment.model.Coordinate;
import com.SCC.environment.model.Map;
import com.SCC.environment.service.RedisService;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ZJDX on 2016/6/20.
 */
@Service("perceptService")
public class PerceptService {
    @Autowired
    RedisDao redisDao;
    @Autowired
    RedisService redisService;

    public void getMapByOpencv(){
        Map map=new Map();
        String path = "D:\\ProgrammingSoftware\\opencv\\opencv\\build\\java\\x64\\opencv_java310.dll";
        System.load(path);
        Mat image = Imgcodecs.imread("D:\\Program\\Environment\\cloud-map\\src\\main\\resources\\mylab-002.pgm");
        Mat image32s= new Mat();
        Imgproc.threshold(image,image32s,5,255,Imgproc.THRESH_BINARY);//white and black, so that no lines out the Contour
        Imgcodecs.imwrite("threshold.png", image32s); // DEBUG
        Imgproc.cvtColor(image32s, image32s, Imgproc.COLOR_BGRA2GRAY, 1);//depth set to 1
        image32s.convertTo(image32s, CvType.CV_32SC1);//no this step: error FindContours supports only CV_8UC1
        Imgcodecs.imwrite("CV_32SC1.png", image32s); // DEBUG
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(image32s, contours, hierarchy,Imgproc.RETR_FLOODFILL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Draw all the contours such that they are filled in.
        Mat contourImg = new Mat(image32s.size(), image32s.type());
        Imgproc.drawContours(contourImg, contours, -1, new Scalar(255, 255, 255), 2);
        System.out.println(contours.size());
        Imgcodecs.imwrite("contourImg.png", contourImg); // DEBUG


        List<Point> points = new ArrayList<Point>();
        //to get the min rec which contains the useful pixel
        double leftX=image32s.cols();
        double leftY=image32s.rows();
        double rightX=0;
        double rightY=0;
        for (MatOfPoint contour : contours) {
            Rect rec = Imgproc.boundingRect(contour);
            // find the best candidates
            if (rec.height > image.height() / 2 & rec.width > image.width() / 2)
                continue;
            Point pt1 = new Point((double) rec.x, (double) rec.y);
            Point center = new Point(rec.x + (double) (rec.width) / 2, rec.y + (double) (rec.height) / 2);
            if(center.x<leftX)leftX=center.x;
            if(center.y<leftY)leftY=center.y;
            if(center.x>rightX)rightX=center.x;
            if(center.y>rightY)rightY=center.y;
            points.add(center);
        }
        map.setMappingX(leftX-20-(leftX-(int)leftX));//由矩形框产生的误差也要算入坐标转换  避免精度丢失
        map.setMappingY(leftY-20-(leftY-(int)leftY));
        /*System.out.println(leftX +","+ leftY);
        System.out.println(rightX +","+ rightY);*/

        Rect recMain=new Rect((int)leftX-20,(int)leftY-20,(int)(rightX-leftX),(int)(rightY-leftY));
        Mat result = image32s.submat(recMain);
        Imgcodecs.imwrite("D:\\Program\\Environment\\cloud-map\\result.png", result);
        List<Coordinate> newPoints=new ArrayList<Coordinate>();

        for (Point p : points) {
            p.x =p.x-map.getMappingX();
            p.y=p.y-map.getMappingY();
            newPoints.add( new Coordinate(p.x,p.y));
        }
        System.out.println("getMapByOpencv x:"+map.getMappingX()+"y:" +map.getMappingY());
        map.setPoints(newPoints);
        map.setId(1);
        redisDao.save("mapId:"+map.getId(),map);
    }

    //get map points without opencv
    public void getMapByReadFile(){
        String filePath = getClass().getResource("/mylab-001.pgm").getPath();
        int picWidth ;
        int picHeight;
        int maxvalue ;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Scanner scan = new Scanner(fileInputStream);
            // Discard the magic number
            scan.nextLine();
             // Discard the comment line
            scan.nextLine();
         // Read pic width, height and max value
            picWidth = scan.nextInt();
            picHeight = scan.nextInt();
             maxvalue = scan.nextInt();
            fileInputStream.close();
            fileInputStream = new FileInputStream(filePath);
            DataInputStream dis = new DataInputStream(fileInputStream);
            // look for 4 lines (i.e.: the header) and discard them
            int numnewlines = 4;
            while (numnewlines > 0) {
                char c;
                do {
                    c = (char)(dis.readUnsignedByte());
                } while (c != '\n');
                numnewlines--;
            }
            // read the image data
            int[][] data2D = new int[picHeight][picWidth];
            for (int row = 0; row < picHeight; row++) {
                for (int col = 0; col < picWidth; col++) {
                    data2D[row][col] = dis.readUnsignedByte();
                    System.out.print(data2D[row][col] + " ");
                }
                System.out.println();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
