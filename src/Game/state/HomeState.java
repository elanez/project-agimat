package Game.state;

import Game.Handler;
import Game.audio.MusicPlayer;
import Game.graphics.Assets;
import Game.ui.UIImageButton;
import Game.ui.UIManager;
import LogInPackage.Edit;
import LogInPackage.Login;
import java.awt.Graphics;

public class HomeState extends State{
    
    private final Thread musicThread;
    private boolean musicRunning;
    private String loginID;

    public HomeState(Handler handler, String loginID) {
        super(handler);
        this.loginID = loginID;
        uiManager = new UIManager(handler);
        musicThread = new Thread(handler.getMusicPlayer(),"..");
        handler.getMouseManager().setUIManager(uiManager);
        musicRunning = handler.getGame().isMusicActive();
        init();
    }
    
    private void init(){
        uiManager.addObject(new UIImageButton(300,200,150,70,Assets.startBtn, () -> {
            if(!musicRunning){
                musicThread.start();
                musicRunning = true;
            }
            
            handler.getMouseManager().setUIManager(null);
            handler.getMenuState().activate();
            State.setState(handler.getGame().getMenuState());
        }));
        
        uiManager.addObject(new UIImageButton(300,300,150,70,Assets.editBtn, () -> {
            Edit page = new Edit(loginID, musicRunning);
            page.setVisible(true);
            handler.getGame().stop();
        }));
        
        uiManager.addObject(new UIImageButton(300,400,150,70,Assets.leaderBoardBtn, () -> {
            handler.getGame().getLeaderBoard().activate();
        }));
        
        uiManager.addObject(new UIImageButton(300,500,150,70,Assets.logoutBtn, () -> {
            Login login = new Login(true);
            login.setVisible(true);
            handler.getGame().stop();
        }));
    }

    @Override
    public void update() {
       
        uiManager.update();
    }

    @Override
    public void render(Graphics graphics){
        graphics.drawImage(Assets.cutSceneBg, 0, 0,handler.getWidth(),handler.getHeight(), null);
        uiManager.render(graphics);
    }
    
    
}

