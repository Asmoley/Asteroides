
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 */
public class Asteroids extends Applet implements KeyListener, ActionListener {

    Image offscreen;
    Graphics offg;
    Spacecraft ship;
    Timer timer;
    boolean upKey, leftKey, rightKey, spacebar, pauseKey, LKey, RKey;
    boolean GiveLife;
    boolean beatLevel = false;
    boolean bossLevel = false;
    ArrayList<Asteroid> asteroidList;
    ArrayList<Bullet> bulletList;
    ArrayList<Debris> debrisList;
    ArrayList<Laser> LaserList;
    int score;
    int level;
    int NumAsteroids;
    int amount = 3;
    int resetCounter = 0;
    AudioClip explosion;
    AudioClip shoot;
    AudioClip firingLaser;
    PowerInvulnerability Invulnerability;

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        ship = new Spacecraft();
        timer = new Timer(20, this);
        score = 0;
        level = 0;
        this.addKeyListener(this);
        resetLevel();
        

    }

    public void resetLevel() {
        
        this.setSize(900, 600);
        ship.xposition = 450;
        ship.yposition = 300;
        ship.yspeed = 0;
        ship.xspeed = 0;
        Invulnerability = new PowerInvulnerability();
        ship.Invulnerable = false;
        Invulnerability.active = true;
        GiveLife = true;
        amount = 3;
        level += 1;
        beatLevel = false;
        bossLevel = false;
        resetCounter = 0;
        NumAsteroids = level * 2;
        asteroidList = new ArrayList();
        bulletList = new ArrayList();
        debrisList = new ArrayList();
        LaserList =  new ArrayList();
        offscreen = createImage(this.getWidth(), this.getHeight());
        offg = offscreen.getGraphics();
        explosion = getAudioClip(getCodeBase(), "AsteroidExplosion.wav");
        shoot = getAudioClip(getCodeBase(), "Gunshot.wav");
        firingLaser = getAudioClip(getCodeBase(), "Laser.wav");
        for (int i = 0; i < NumAsteroids; i++) {
            asteroidList.add(new Asteroid());
        }

    }
    public void resetBossLevel(){
        ship.xposition = 450;
        ship.yposition = 300;
        ship.yspeed = 0;
        ship.xspeed = 0;
        Invulnerability = new PowerInvulnerability();
        ship.Invulnerable = false;
        Invulnerability.active = true;
        GiveLife = true;
        amount = 3;
        level += 1;
        beatLevel = false;
        bossLevel = false; 
        resetCounter = 0;
        asteroidList.add(new Asteroid(600,300,10));
        bulletList = new ArrayList();
        debrisList = new ArrayList();
        LaserList =  new ArrayList();
        offscreen = createImage(this.getWidth(), this.getHeight());
        offg = offscreen.getGraphics();
        explosion = getAudioClip(getCodeBase(), "AsteroidExplosion.wav");
        shoot = getAudioClip(getCodeBase(), "Gunshot.wav");
        firingLaser = getAudioClip(getCodeBase(), "Laser.wav");
     
    }
    public void resetGame() {
        level = 0;
        score = 0;
        ship.lives = 2;
        resetLevel();
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void actionPerformed(ActionEvent e) {
        keyCheck();
        respawn();
        if(beatLevel == true){
            resetCounter += 1;
        }  
        if (bossLevel == true) {
            resetCounter += 1;
        }
        if(RKey == true){
            resetGame();
        }
        ship.updatePosition();
        Invulnerability.updatePosition();
        checkCollision();
        CheckBonusLife();
        
        if (ship.Invulnerable == true) {
            Invulnerability.counter += 1;
        }
        if (ship.Invulnerable == true && Invulnerability.counter > 500) {
            ship.Invulnerable = false;
        }
        checkAsteroidDestruction();
        for (int i = 0; i < debrisList.size(); i++) {
            debrisList.get(i).updatePosition();
            if (debrisList.get(i).counter == 60) {
                debrisList.remove(i);
            }
        }
        for (int i = 0; i < asteroidList.size(); i++) {
            asteroidList.get(i).updatePosition();
        }
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).updatePosition();
            if (bulletList.get(i).counter == 35 || bulletList.get(i).active == false || ship.active == false) {
                bulletList.remove(i);
            }
        }
        for (int i = 0; i < LaserList.size(); i++) {
            LaserList.get(i).updatePosition();
            if(LaserList.get(i).counter == 60){
                LaserList.remove(i);
            }
        }
    }

    public void paint(Graphics g) {
        offg.setColor(Color.BLACK);
        offg.fillRect(0, 0, 900, 600);
        if (pauseKey == true){
            offg.setColor(Color.GREEN);
            offg.drawString("Paused", 450, 300);
        }
        if (beatLevel == true){
            if (resetCounter < 150){
                offg.setColor(Color.GREEN);
                offg.drawString("New Level", 450, 300);
            }else {
                resetLevel();
            }
        }
        if (bossLevel == true) {
           if (resetCounter < 150){
                offg.setColor(Color.GREEN);
                offg.drawString("Boss Level!!!", 450, 300);
            }else {
                resetBossLevel();
            } 
        }
        if (ship.Invulnerable == true){
            offg.setColor(Color.YELLOW);
            paintStuff();
        }else{
            offg.setColor(Color.GREEN);
            paintStuff();
        }
        
        g.drawImage(offscreen, 0, 0, this);
        repaint();
    }

    public void update(Graphics g) {
        paint(g);   
    }

    public void keyCheck() {
        if (upKey) {
            ship.accelerate();
        }
        if (leftKey) {
            ship.rotateLeft();
        }
        if (rightKey) {
            ship.rotateRight();
        }
        if (spacebar) {
            shoot();
        }  
        
        if(LKey){
            fireLaser();
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightKey = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftKey = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upKey = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacebar = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {
            LKey = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            RKey = true;
        }   
        if (e.getKeyCode() == KeyEvent.VK_P) {
            if(pauseKey == false){
                stop();
                pauseKey = true;
            }else{
                start();
                pauseKey = false;
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightKey = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftKey = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upKey = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacebar = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {
            LKey = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            RKey = false;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public boolean collision(VectorSprite thing1, VectorSprite thing2) {
        int x, y;
        for (int i = 0; i < thing1.drawShape.npoints; i++) {
            x = thing1.drawShape.xpoints[i];
            y = thing1.drawShape.ypoints[i];
            if (thing2.drawShape.contains(x, y)) {
                return true;
            }

        }
        for (int i = 0; i < thing2.drawShape.npoints; i++) {
            x = thing2.drawShape.xpoints[i];
            y = thing2.drawShape.ypoints[i];
            if (thing1.drawShape.contains(x, y)) {
                return true;
            }

        }
        return false;
    }

    public void checkCollision() {
        for (int i = 0; i < asteroidList.size(); i++) {
            if (collision(ship, asteroidList.get(i)) == true && ship.active == true && ship.Invulnerable == false) {
                ship.hit();
                ship.lives -= 1;
            }

            for (int j = 0; j < bulletList.size(); j++) {
                if (collision(asteroidList.get(i), bulletList.get(j))) {
                    asteroidList.get(i).active = false;
                    bulletList.get(j).active = false;
                }
            }
            for (int k = 0; k < debrisList.size(); k++) {
                if (collision(debrisList.get(k), ship) == true && ship.active == true && ship.Invulnerable == false) {
                    ship.hit();
                    ship.lives -= 1;
                }

            }
            for (int l = 0; l < LaserList.size(); l++) {
                if (collision(LaserList.get(l),asteroidList.get(i))) {
                    asteroidList.get(i).active = false;
                }
            }

        }
        if (collision(ship, Invulnerability) && Invulnerability.active == true && ship.active == true) {
            Invulnerability.active = false;
            ship.Invulnerable = true;
            Invulnerability.counter = 0;
        }
    }

    public void respawn() {
        if (ship.active == false && ship.counter > 90 && safeRespawn()) {
            ship.reset();
        }

    }

    public boolean safeRespawn() {
        int x, y, h;
        for (int i = 0; i < asteroidList.size(); i++) {
            x = (int) (asteroidList.get(i).xposition - 450);
            y = (int) (asteroidList.get(i).yposition - 300);
            h = (int) Math.sqrt(x * x + y * y);
            if (h < 75) {
                return false;
            }
        }
        return true;
    }

    public void shoot() {
        if (ship.counter > 20 && ship.active == true) {
            bulletList.add(new Bullet(ship.xposition, ship.yposition, ship.angle));
            shoot.play();
            ship.counter = 0;
        }
    }
    public void fireLaser(){
        if(ship.counter > 30 && amount > 0) {
            LaserList.add(new Laser(ship.xposition,ship.yposition,ship.angle));
            firingLaser.play();
            amount -= 1;
            ship.counter = 0;
        }
    }

    public void checkAsteroidDestruction() {
        for (int i = 0; i < asteroidList.size(); i++) {
            asteroidList.get(i).updatePosition();
            if (asteroidList.get(i).active == false) {
                if (asteroidList.get(i).size > 1) {
                    asteroidList.add(new Asteroid(asteroidList.get(i).xposition, asteroidList.get(i).yposition,
                            asteroidList.get(i).size - 1));
                    asteroidList.add(new Asteroid(asteroidList.get(i).xposition, asteroidList.get(i).yposition,
                            asteroidList.get(i).size - 1));
                }
                for (int j = 0; j < 15; j++) {
                    debrisList.add(new Debris(asteroidList.get(i).xposition, asteroidList.get(i).yposition));
                }
                score += 150 / asteroidList.get(i).size;
                asteroidList.remove(i);
                explosion.play();
            }
        }
    }
    public void paintStuff(){
        if (RKey == false){
            if (asteroidList.size() == 0 && level != 5) {
                beatLevel = true;
            }
            if (asteroidList.size() == 0 && level == 5) {
                bossLevel = true;
            }
            if (asteroidList.size() == 0 && level == 6) {
                ship.active = false;
                offg.drawString("You Win", 450, 300);
            }
            if (ship.lives > -1) {
                offg.drawString("Level: " + level, 855, 10);
                offg.drawString("Score: " + score, 450, 10);
                offg.drawString("Lasers: " + amount, 1, 595);
                if (ship.active) {
                ship.paint(offg);
                }
                if (Invulnerability.active == true) {
                Invulnerability.paint(offg);
                }
                for (int i = 0; i < asteroidList.size(); i++) {
                    asteroidList.get(i).paint(offg);
                }
                for (int i = 0; i < bulletList.size(); i++) {
                    bulletList.get(i).paint(offg);
                }
                for (int i = 0; i < debrisList.size(); i++) {
                    debrisList.get(i).paint(offg);
                }
                for (int i = 0; i < LaserList.size(); i++) {
                    LaserList.get(i).paint(offg);
                }
                offg.drawString("lives: " + ship.lives, 1, 10);
            } else {
                offg.drawString("Score: " + score, 375, 30);
                offg.setColor(Color.RED);
                Font bigText = new Font("Courier New", 1, 50);
                offg.setFont(bigText);
                offg.drawString("You Lose", 375, 300);
            }
        }
    }
    public void CheckBonusLife(){
        if (score == 2500 && GiveLife == true || score == 10000 && GiveLife == true || score == 25000 && GiveLife == true || score == 50000 && GiveLife == true){
            GiveLife = false;
            ship.lives += 1;
        }
    }
}
