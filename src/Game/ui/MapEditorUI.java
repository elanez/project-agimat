/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.ui;

import Game.Handler;
import Game.graphics.Assets;
import Game.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Elan Fernandez
 */
public final class MapEditorUI {
   
    private final Handler handler;
    private final UIManager uiManager;
    
    private ArrayList<UIObject> obj;
    private final float x, y;
    private final double space;
    private final int width, height, max;
    private int len, index, prevIndex, count;
    private boolean active;
    private final int rectX, rectY, rectWidth, rectHeight;
    private final Rectangle rect;
    
    public MapEditorUI(Handler handler){
        this.handler = handler;
        
        uiManager = new UIManager(handler);
        
        rectX = 30;
        rectY = handler.getHeight() - 120;
        rectWidth = handler.getWidth()- 60;
        rectHeight = 100;
        
        rect = new Rectangle(rectX, rectY, rectWidth, rectHeight);
        
        max = 12;
        
        width = Tile.TILE_WIDTH;
        height = Tile.TILE_HEIGHT;
        x = 30;
        y = rectY + (rectHeight-height)/2;
        space = (rectWidth - (width * max))/max;
        
        index = 0;
        count = 0;
        
        init();
    }
    
    public void init(){
       // grass, dirt, cliff, sand, water, plant, deepWater, plank, rock
        addObj(Assets.grass, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(0);
        });
        
        addObj(Assets.dirt, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(1);
        });
        
        addObj(Assets.cliff, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(2);
        });
        
        addObj(Assets.sand, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(3);
        });
        
        addObj(Assets.water, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(4);
           // handler.getWorld().setEntityID(0);
        });
        addObj(Assets.plant, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(5);
           // handler.getWorld().setEntityID(0);
        });
        addObj(Assets.deepWater, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(6);
           // handler.getWorld().setEntityID(0);
        });
        addObj(Assets.plank, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(7);
           // handler.getWorld().setEntityID(0);
        });
        addObj(Assets.rock, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(8);
           // handler.getWorld().setEntityID(0);
        });
        
        addObj(Assets.homePortal, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(9);
        });
        
        addObj(Assets.portal1, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(14);
        });
        
        addObj(Assets.portal2, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(15);
        });
        
        addObj(Assets.portal3, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(16);
        });
         
         addObj(Assets.waveLeft, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(10);
        });
         addObj(Assets.waveRight, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(11);
        });
         addObj(Assets.waveUp, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(12);
        });
         addObj(Assets.waveDown, () -> {
            handler.getWorld().setTileInput(true);
            handler.getWorld().setTileFill(13);
        });
        
        addObj(Assets.kubo1, () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(4);
        });
        
        addObj(Assets.kubo2, () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(5);
        });
        
        addObj(Assets.kubo3, () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(6);
        });
        
        addObj(Assets.ship, () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(14);
        });
        
        addObj(Assets.tree, () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(1);
        });
        addObj(Assets.coconut, () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(3);
        });
        addObj(Assets.banana, () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(2);
        });
        addObj(Assets.pigAttack_left, () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(7);
        });
        addObj(Assets.enemyAttack_left, () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(8);
        });
        addObj(Assets.aswangAttack_left, () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(9);
        });
        addObj(Assets.bossLeft, () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(10);
        });
        addObj(Assets.shopNPC, () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(11);
        });
        addObj(Assets.npc1_down[0], () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(12);
        });
        addObj(Assets.npc2_down[0], () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(13);
        });
        
        addObj(Assets.spaniardDown[0], () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(15);
        });
        
        addObj(Assets.npc2_down, () -> {
            handler.getWorld().setTileInput(false);
            handler.getWorld().setEntityID(16);
        });
        
        obj = uiManager.getObjects();
        len = obj.size();
        
        
        for(int i=0; i < len ; i++){
            if(i<max)
                continue;
            
            obj.get(i).setActive(false);
        }
        
    }
    
    public void update(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_V) && !handler.getWorld().getPlayer().notMoveable()){
            active = !active;
            if(active){
                handler.getWorld().getEntityManager().setPause(true);
                handler.getMouseManager().setUIManager(uiManager);
                handler.getWorld().getPlayer().setSpeed(20);
            }else{
                handler.getWorld().getEntityManager().setPause(false);
                handler.getMouseManager().setUIManager(null);
                handler.getWorld().getPlayer().setSpeed(5);
            }
        }

        if(active){
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)){
                if((index - max) >= 0){
                    activate(index);
                    index-=max;
                    activate(index);
                }
                    
            }else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)){
                if((index + max) < len){
                    activate(index);
                    index += max;
                    activate(index);
                }
                    
            }
        }
    
        uiManager.update();
            
    }
    
    public void render(Graphics graphics){
        if(!active)
            return;
       
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(rectX, rectY, rectWidth, rectHeight);
        
        count = 0;
        prevIndex = index;
        
        while(index<len){
            obj.get(index).render(graphics);
            index++;
            count++;
            if(count >= max)
                break;
        }
        
        index = prevIndex;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    private void addObj(BufferedImage image, ClickListener click){
        uiManager.addObject(new UIImageButton((int)(x + (space * (count+1)) + (width*count)),y,width,height,image,click));
        count++;
        if(count >= max)
            count = 0;
        
    }
    
   private void addObj(BufferedImage[] image, ClickListener click){
        uiManager.addObject(new UIImageButton(((int)(x + (space * (count+1)) + (width*count))),y,width,height,image,click));
        count++;
        if(count >= max)
            count = 0;
        
    }
    
   public void activate(int i){
       int j=0;
       while(i < len){
           obj.get(i).setActive(!(obj.get(i).isActive()));
           i++;
           j++;
           if(j >= max)
               break;
       }
   }

    public Rectangle getRect() {
        return rect;
    }
   
   
}


