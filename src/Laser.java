/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics;
import java.awt.Polygon;
/**
 *
 * @author RealProgramming4Kids
 */

public class Laser extends VectorSprite{
    public Laser(double x, double y, double a){
        shape = new Polygon();
        shape.addPoint (0,0);
        shape.addPoint (200,0);
        shape.addPoint (1,0);
        
        drawShape = new Polygon();
        drawShape.addPoint (0,0);
        drawShape.addPoint (200,0);
        drawShape.addPoint (1,0);
        
        active = true;
        xposition = x;
        yposition = y;
        angle = a;
        THRUST = 20;
        xspeed = Math.cos(angle) * THRUST;
        yspeed = Math.sin(angle) * THRUST;
    }
    
}
