
import java.awt.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RealProgramming4Kids
 */
public class Debris extends VectorSprite {
    public Debris(double x, double y){
        xposition = x;
        yposition = y;
        shape = new Polygon();
        shape.addPoint(2,2);
        shape.addPoint(2,2);
        shape.addPoint(2,2);
        shape.addPoint(2,2);
        shape.addPoint(2,2);
        drawShape = new Polygon();
        drawShape.addPoint(2,2);
        drawShape.addPoint(2,2);
        drawShape.addPoint(2,2);
        drawShape.addPoint(2,2);
        drawShape.addPoint(2,2);
        ROTATION = 0.05;
        THRUST = 1.5;
        double h, a;
        h = Math.random();
        a = Math.random() * 2*Math.PI;
        xspeed = Math.cos(a)*h;
        yspeed = Math.sin(a)*h;
    }
   
}
