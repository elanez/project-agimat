/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.state;

import Game.Handler;
import Game.graphics.Assets;
import Game.graphics.Text;
import Game.ui.UIImageButton;
import Game.ui.UIManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author Elan Fernandez
 */
public class HowToPlay extends State{
        private final Thread musicThread;
        private int btnX;

    public HowToPlay(Handler handler) {
        super(handler);
        this.handler = handler;
        uiManager = new UIManager(handler);
        musicThread = new Thread(handler.getMusicPlayer(),"..");
        btnX = handler.getWidth()/2;
        init();
    }
    
    private void init(){
        uiManager.addObject(new UIImageButton(0,0,32,32,Assets.closeBtn, () -> {
            handler.getMenuState().activate();
        }));
    }
 

    @Override
    public void update() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
            handler.getMenuState().activate();
        }
        uiManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.loginBg, 0, 0,handler.getWidth(),handler.getHeight(), null);
        uiManager.render(graphics);
        graphics.drawImage(Assets.btnW, btnX, 50, 64,64, null);
        Text.drawString(graphics, "MOVE UPWARD -", 230, 75, true, Color.black, Assets.font28);
        graphics.drawImage(Assets.btnS, btnX, 120, 64, 64, null);
        Text.drawString(graphics, "MOVE DOWNWARD -", 250 , 155, true, Color.black, Assets.font28);
        graphics.drawImage(Assets.btnA, btnX, 190,64, 64, null);
        Text.drawString(graphics, "MOVE TO THE LEFT -", 260 , 215, true, Color.black, Assets.font28);
        graphics.drawImage(Assets.btnD, btnX, 260,64, 64, null);
        Text.drawString(graphics, "MOVE TO THE RIGHT -", 270 , 295, true, Color.black, Assets.font28);
        graphics.drawImage(Assets.btnK, btnX, 330,64, 64, null);
        Text.drawString(graphics, "LONG RANGE ATTACK -", 270 , 365, true, Color.black, Assets.font28);
        Text.drawString(graphics, "KEY LEGENDS", btnX , 20, true, Color.black, Assets.font28);
        graphics.drawImage(Assets.btnJ, btnX, 400, 64,64, null);
        Text.drawString(graphics, "NORMAL ATTACK -", 270 , 425, true, Color.black, Assets.font28);
        graphics.drawImage(Assets.btnL, btnX, 470, 64,64, null);
        Text.drawString(graphics, "SPIN -", 270 , 495, true, Color.black, Assets.font28);
        graphics.drawImage(Assets.btnShift, btnX, 540, 64,64, null);
        Text.drawString(graphics, "ROLL -", 270 , 585, true, Color.black, Assets.font28);
    }
    

}
