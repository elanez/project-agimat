/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.dialog;

import Game.Handler;
import Game.entity.Entity;
import Game.entity.npc.NPC;
import Game.graphics.Animation;
import Game.graphics.Assets;
import Game.graphics.Text;
import Game.tiles.Tile;
import Game.ui.UIImageButton;
import Game.ui.UIManager;
import Game.util.Timer;
import Game.util.Utils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author Elan Fernandez
 */
public class InteractDialog {

    private final Handler handler;
    private boolean active, infoActive;
    private String message, npcName;
    private final Timer interactTimer;
    private final int x,y,width,height;
    private final int messageX, messageY, nameX, nameY;
    private final int displayX, displayY, displayW, displayH;
    
    private final Animation anim1, anim2, anim3;
    private final UIManager uiManager;
    
    public InteractDialog(Handler handler) {
        this.handler = handler;
        
        uiManager = new UIManager(handler);
        active = false;
        
        interactTimer = new Timer((long) 0.1);
        
        message = "...";
        npcName = " ";
        x = 10;
        y = ((handler.getHeight()*3)/4) - 20;
        width = handler.getWidth() - 20;
        height = handler.getHeight()/4;
        
        messageX = width/2;
        messageY = height/2 + y;
        nameX = x * 2;
        nameY = y + 20;
        
        displayX = (handler.getWidth() - 20)/4;
        displayY = (handler.getHeight() - 20)*9/32;
        displayW = handler.getWidth()/3;
        displayH = 120;
        
        anim1 = new Animation(200,Assets.portal1);
        anim2 = new Animation(200,Assets.portal2);
        anim3 = new Animation(200,Assets.portal3);
        
        init();
        
    }
    
    private void init(){
        uiManager.addObject(new UIImageButton(displayX, displayY, displayW, displayH,Assets.stage1, () -> {
            
        }));
        
        uiManager.addObject(new UIImageButton(displayX, displayY + displayH + 20, displayW, displayH,Assets.stage2, () -> {
            
        }));
        
        uiManager.addObject(new UIImageButton(displayX, displayY + displayH*2 + 40, displayW, displayH,Assets.stage3, () -> {
            
        }));
        
        uiManager.addObject(new UIImageButton(handler.getWidth()-52, 20, 32, 32,Assets.closeBtn, () -> {
            infoSwitch();
        }));
    }
    
    public void interact(){
        
        if(interactTimer.start()){
            if(handler.getWorld().getPlayer().getInventory().isActive())
                return;
            
            Rectangle interactRange = Utils.generateHitbox(handler.getWorld().getPlayer(), 20, handler.getWorld().getPlayer().getFace());
            
            interactTimer.restart();
            
            for(Entity e : handler.getWorld().getEntityManager().getNpcs()){
                if(e.getCollisionBounds(0,0).intersects(interactRange)){
                    ((NPC)e).interact();
                    return;
                }
            }
            active = false;
        }
       
    }
    
    public void update(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F) && !handler.getWorld().getShop().isActive()){
            if(handler.getWorld().getEntityManager().isPlayerDead()){
                handler.getWorld().getEntityManager().setPlayerDead(false);
                handler.setWorld(handler.getState().getWorldManager().getWorlds().get(0));
                handler.getWorld().getPlayer().setX(12*Tile.TILE_WIDTH);
                handler.getWorld().getPlayer().setY(12*Tile.TILE_HEIGHT);
                handler.getWorld().getPlayer().setHealth(100);
                handler.getWorld().getPlayer().setStamina(100);
                handler.getWorld().getPlayer().setSlow(false);
                handler.getWorld().getPlayer().setSpeed(5);
                handler.getWorld().getPlayer().getQuest().reset();
                handler.getGameState().getWorldManager().reset();
            }
            interact();
        }else if(infoActive){
            anim1.update();
            anim2.update();
            anim3.update();
            uiManager.update();
        }
        
    }
    
    public void render(Graphics graphics){
        if(active){
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(x, y, width, height);
        Text.drawString(graphics, message, messageX, messageY, true, Color.BLACK, Assets.font28);
        Text.drawString(graphics, npcName, nameX, nameY, false, Color.BLACK, Assets.font28);
        }
        
        renderInfo(graphics);
    }
    
    public void renderInfo(Graphics graphics){
        if(infoActive){
            
            graphics.drawImage(Assets.ShopInventoryP,20, 20, handler.getWidth() - 40, handler.getHeight() - 40, null);
            Text.drawString(graphics, "Information", (handler.getWidth() - 40)/2, 80, true, Color.white, Assets.font35);
//            graphics.drawImage(Assets.stage1, displayX, displayY, displayW, displayH, null);
            graphics.drawImage(anim1.getCurrentFrame(), displayX + displayW + 20, displayY, displayH, displayH, null);
//            graphics.drawImage(Assets.stage2, displayX, displayY + displayH + 20, displayW, displayH, null);
            graphics.drawImage(anim2.getCurrentFrame(), displayX + displayW + 20, displayY + displayH + 20, displayH, displayH, null);
//            graphics.drawImage(Assets.stage3, displayX, displayY + displayH*2 + 40, displayW, displayH, null);
            graphics.drawImage(anim3.getCurrentFrame(), displayX + displayW + 20 , displayY + displayH*2 + 40, displayH, displayH, null);
            uiManager.render(graphics);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void activeSwitch(){
        active = !active;
    }
    
    public void infoSwitch(){
        infoActive = !infoActive;
        if(infoActive)
            handler.getMouseManager().setUIManager(uiManager);
        else
            handler.getMouseManager().setUIManager(null);
    }
    
    public void setDialog(String npcName, String message){
        this.npcName = npcName;
        this.message = message;
    }  

    public String getMessage() {
        return message;
    }

    public String getNpcName() {
        return npcName;
    }
    
}
