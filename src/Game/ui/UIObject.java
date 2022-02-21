/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
 *
 * @author Elan Fernandez
 */
public abstract class UIObject {
    
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean hovering=false;
    protected boolean active;
    
    public UIObject(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int)x, (int)y, width, height);
        
        active = true;
    }
    
    public abstract void update();
    public abstract void render(Graphics graphics);
    public abstract void onClick();
    
    public void onMouseMove(MouseEvent e){
        if(bounds.contains(e.getX(), e.getY()))
            hovering = true;
        else
            hovering = false;
    }
    
    public void onMouseRelease(MouseEvent e){
        if(hovering && active)
            onClick();
    }
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        bounds.x = (int)x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        bounds.y = (int)y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        bounds.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        bounds.height = height;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

    public boolean isActive() {
        return active;
   }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
}
