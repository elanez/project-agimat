/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.entity.npc;

import Game.Handler;
import Game.graphics.Assets;
import Game.tiles.Tile;
import Game.ui.UIImageButton;
import Game.ui.UIManager;
import java.awt.Graphics;

/**
 *
 * @author Elan Fernandez
 */
public class NPC1 extends NPC{
    
    private final UIManager uiManager;
    
    private boolean btnStatus;
    private final float btnX, btnY;
    private final int btnW, btnH;
    
    public NPC1(Handler handler, float x, float y, int id){
        super(handler, x, y, id);
        
        bounds.x = width/2 - width/6;
        bounds.y = height/2;
        bounds.width = width/3;
        bounds.height = height/2;
        
        name = "Pedro";
        message = "Do you have a minute";
        
        btnStatus = false;

        btnW = Tile.TILE_WIDTH;
        btnH = Tile.TILE_HEIGHT;
        btnX = ((handler.getWidth() - 20)*5)/6;
        btnY = ((handler.getHeight()*3)/4) - 20 - btnH;
        
        uiManager = new UIManager(handler);
        
        init();
    }

    @Override
    public void interact() {
        handler.getWorld().getPlayer().getDialog().setDialog(name, message);
        handler.getWorld().getPlayer().getDialog().activeSwitch();
            
        if(!handler.getWorld().getPlayer().getDialog().isActive()){
            btnStatus = false;
            handler.getMouseManager().setUIManager(null);
        }else{
            btnStatus = true;
            handler.getMouseManager().setUIManager(uiManager);
        }
            
    }

    @Override
    public void update() {
       if(btnStatus)
            uiManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.npc1_down[0], (int)(x - handler.getCamera().getxOffset()), (int)(y -  handler.getCamera().getyOffset()),width,height, null);
        
        if(btnStatus)
            uiManager.render(graphics);
    }
    
    public void init(){
        uiManager.addObject(new UIImageButton(btnX,btnY, btnW, btnH,Assets.acceptBtn, () -> {
            handler.getWorld().getPlayer().getDialog().setActive(false);
            handler.getWorld().getQuest().activate();
            btnStatus = false;
            
        }));
        
        uiManager.addObject(new UIImageButton(btnX + btnW + 30,btnY, btnW, btnH,Assets.cancelBtn, () -> {
            handler.getWorld().getPlayer().getDialog().setActive(false);
            btnStatus = false;
            
        }));
    }
}
