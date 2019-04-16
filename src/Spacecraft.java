
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


public class Spacecraft extends VectorSprite {
    int lives;
    boolean Invulnerable;
    
    public Spacecraft(){
        shape = new Polygon();
        shape.addPoint(25, 0);
        shape.addPoint(-10, 15);
        shape.addPoint(-10, -15);
        drawShape = new Polygon();
        drawShape.addPoint(25,0);
        drawShape.addPoint(-10,15);
        drawShape.addPoint(-10,-15);
        xposition = 450;
        yposition = 300;
        ROTATION = 0.15;
        THRUST = 0.5;
        active = true;
        lives = 2;
        Invulnerable = false;
    }
    public void accelerate(){
        xspeed += Math.cos(angle)*THRUST;
        yspeed += Math.sin(angle)*THRUST;
    }
    
    public void rotateLeft(){
        angle -= ROTATION;
    }
    public void rotateRight(){
        angle += ROTATION;
    }
    public void hit(){
        active = false;
        counter = 0;
        
    }
    public void reset(){
        xposition = 450;
        yposition = 300;
        active = true;
        xspeed = 0;
        yspeed = 0;
        angle = 0;
    }

}

