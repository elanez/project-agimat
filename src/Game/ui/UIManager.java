/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.ui;

import Game.Handler;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Elan Fernandez
 */
public class UIManager {
    
    private Handler handler;
    private ArrayList<UIObject> objects;
    
    public UIManager(Handler handler){
        this.handler = handler;
        objects = new ArrayList<>();
    }
    
    public void update(){
        objects.forEach((o) -> {
            o.update();
        });
    }
    
    public void render(Graphics graphics){
        objects.forEach((o) -> {
            o.render(graphics);
        });
    }
    
    public void onMouseMove(MouseEvent e){
        objects.forEach((o) -> {
            o.onMouseMove(e);
        });
    }
    
    public void onMouseRelease(MouseEvent e){
        objects.forEach((o) -> {
            o.onMouseRelease(e);
        });
    }
    
    public void addObject(UIObject o){
        objects.add(o);
    }
    
    public void removeObject(UIObject o){
        objects.remove(o);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.objects = objects;
    }
}
