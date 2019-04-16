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
public class Bullet extends VectorSprite{
    public Bullet(double x, double y, double a){
        shape = new Polygon();
        shape.addPoint(5, 0);
        shape.addPoint(-1, 1);
        shape.addPoint(-1, -1);
        drawShape = new Polygon();
        drawShape.addPoint(5,0);
        drawShape.addPoint(-1,1);
        drawShape.addPoint(-1,-1);
        xposition = x;
        yposition = y;
        angle = a;
        THRUST = 10;
        xspeed = Math.cos(angle)*THRUST;
        yspeed = Math.sin(angle)*THRUST;
        active = true;
    }
}
