/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Projectile;

import Game.Handler;
import Game.graphics.Animation;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kevin
 */
public class Projectile {
    public int x, y;
    public int speed = 20;
    public int size = 150;
    public int face;
    public Animation projectileAnimation;
    public Rectangle bounds;
    public Handler handler;
    public Projectile (Handler handler, int x, int y, int face, Animation projectileAnimation){
        this.x = x;
        this.y = y;
        this.face = face;
        this.handler = handler;
        this.projectileAnimation = projectileAnimation;
        bounds = new Rectangle ((int)x,(int)y, size, size);
    }
    
    public void update(){
        move();
       // System.out.println("nani");
        projectileAnimation.update();
    }
    
    public void move(){
        switch(face){
            case 0:
                x -= speed;
                break;
            case 1:
                x += speed;
                break;
            case 2:
                y -= speed;
                break;
            case 3:
                y += speed;
                break;
            case 4:
                x -= speed/2;
                y += speed/2;
                break;
            case 5:
                x -= speed/2;
                y -= speed/2;
                break;
            case 6: 
                x += speed/2;
                y += speed/2;
                break;
            case 7:
                x += speed/2;
                y -= speed/2;
                break;
        
        }
    }
    
    public void render(Graphics graphics){
        graphics.drawImage(getCurrentAnimationFrame(),(int)(x - handler.getCamera().getxOffset()) , (int)(y - handler.getCamera().getyOffset()),size,size, null); 
    }
    
    public BufferedImage getCurrentAnimationFrame(){
        return projectileAnimation.getCurrentFrame(face);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }



    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    
    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()),bounds.width, bounds.height);
    }
}
