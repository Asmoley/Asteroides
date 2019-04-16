
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
public class Asteroid extends VectorSprite {
    public Asteroid(){
        size = 3;
        initializeAsteroid();
}
    public void updatePosition(){
        angle += ROTATION;
        super.updatePosition();
    }
    public Asteroid(double x, double y, int s){
        size = s;
        initializeAsteroid();
        xposition = x;
        yposition = y;
        
    }
    public void initializeAsteroid(){
        shape = new Polygon();
        shape.addPoint(11 * size, 2 * size);
        shape.addPoint(3 * size, 13 * size);
        shape.addPoint(-9 * size, 4 * size);
        shape.addPoint(-7 * size, -6 * size);
        shape.addPoint(8 * size, -13 * size);
        drawShape = new Polygon();
        drawShape.addPoint(30 * size,2 * size);
        drawShape.addPoint(5 * size,35 * size);
        drawShape.addPoint(-25 * size,10 * size);
        drawShape.addPoint(-17 * size, -15 * size);
        drawShape.addPoint(20 * size, -35 * size);
        ROTATION = 0.05;
        THRUST = 0.5;
        double h, a;
        h = Math.random();
        a = Math.random() * 2*Math.PI;
        xspeed = Math.cos(a)*h;
        yspeed = Math.sin(a)*h;
        h = Math.random() * 400 + 100;
        a = Math.random() * 2*Math.PI;
        xposition = Math.cos(a) *h + 450;
        yposition = Math.sin(a) *h + 300;
        active = true;
        
    }
}
