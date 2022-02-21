package Game.state;

import Game.Handler;
import Game.graphics.Animation;
import Game.graphics.Assets;
import Game.ui.UIImageButton;
import Game.ui.UIManager;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class MenuState extends State{
    
    private final Thread musicThread;
    private final Animation anim;
    
    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        
        musicThread = new Thread(handler.getMusicPlayer(),"..");
        handler.getMouseManager().setUIManager(uiManager);
        
        anim = new Animation(200,Assets.player_right);
        init();
    }
    
    private void init(){
        uiManager.addObject(new UIImageButton(300,250,150,70,Assets.playBtn, () -> {
//            musicThread.start();
            handler.getGameState().load();
            handler.getMouseManager().setUIManager(null);
            State.setState(handler.getGame().getGameState());
        }));
        
        uiManager.addObject(new UIImageButton(300,350,150,70,Assets.tutorialsBtn, () -> {
           handler.getGame().getHowToPlay().activate();
        }));
        
        uiManager.addObject(new UIImageButton(300,450,150,70,Assets.backBtn, () -> {
            handler.getMouseManager().setUIManager(null);
            handler.getGame().getHomeState().activate();
            State.setState(handler.getGame().getHomeState());
        }));
    }

    @Override
    public void update() {
        
        anim.update();
        uiManager.update();
    }

    @Override
    public void render(Graphics graphics){
        graphics.drawImage(Assets.lore, 0, 0,handler.getWidth(),handler.getHeight(), null);
        graphics.drawImage(anim.getCurrentFrame(), 0, handler.getHeight()/4,handler.getWorld().getPlayer().getWidth() * 3,handler.getWorld().getPlayer().getHeight() * 3, null);
        uiManager.render(graphics);
    }
    
    
}
