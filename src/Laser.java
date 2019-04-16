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
    int amount;
    public Laser(double x, double y, double a){
        shape = new Polygon();
        shape.addPoint (0,0);
        shape.addPoint (75,75);
        shape.addPoint (1,0);
        
        drawShape = new Polygon();
        drawShape.addPoint (0,0);
        drawShape.addPoint (75,75);
        drawShape.addPoint (1,0);
        
        active = false;
        
        xposition = x;
        yposition = y;
        angle = a;
        amount = 3;
    }
    
}
