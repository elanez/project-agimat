package Game;
import Game.graphics.Assets;
import Game.graphics.Camera;
import Game.input.KeyManager;
import Game.state.GameState;
import Game.state.MenuState;
import Game.state.State;
import Game.input.MouseManager;
import Game.items.Item;
import Game.state.HomeState;
import Game.state.HowToPlay;
import Game.state.LeaderBoard;
import Game.state.OptionState;
import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    
    private final JFrame frame = new JFrame("GAME");
    private final Canvas canvas = new Canvas();
    private boolean running = false;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private Thread thread;
    private boolean musicActive;
    
    private final Camera camera;    
    private final Handler handler;
    private final KeyManager keyManager;    
    private final MouseManager mouseManager;
    private final State howToPlay;
    private final State gameState;
    private final State menuState;
    private final State optionState;
    private final State homeState;
    private final State leaderBoard;
    
    public Game(String id, boolean musicActive){
        this.musicActive = musicActive;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.add(canvas);
        frame.setVisible(true);
        frame.setResizable(false);
        canvas.createBufferStrategy(2);
        
        Assets.init();
               
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
       
        frame.addKeyListener(keyManager);
        frame.addMouseListener(mouseManager);
        frame.addMouseMotionListener(mouseManager);
        canvas.addMouseListener(mouseManager);
        canvas.addMouseMotionListener(mouseManager);
        
        canvas.setFocusable(false);
        
        handler = new Handler(this);
        camera = new Camera(handler,0,0);
        Item.init(handler);
        
        optionState = new OptionState(handler, id);
        howToPlay = new HowToPlay(handler);
        gameState = new GameState(handler, id);
        leaderBoard = new LeaderBoard(handler);
        menuState = new MenuState(handler);
        homeState = new HomeState(handler, id);
        State.setState(homeState);
    }    
   
    public void update(){
        keyManager.update();
        
        if(State.getState() != null){
            State.getState().update();
        }
    }
    
    public void render(){
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
        
        graphics.clearRect(0,0,frame.getWidth(),frame.getHeight());
        
        if(State.getState() != null){
            State.getState().render(graphics);
        }
        
        bufferStrategy.show();
        graphics.dispose();
    }
    
    @Override
    public void run(){
        running = true;
        int fps=0;
        int updates=0;
        long lastTime = System.nanoTime(); //long 2^63
        double nanoSecondConversion = 1000000000.0 / 60; //60 frames per second
        double changeInSeconds = 0;
        long now;
        long timer = System.currentTimeMillis();
        
        while(running){
            now = System.nanoTime();
            changeInSeconds += (now - lastTime) / nanoSecondConversion;
            lastTime = now;
            if(changeInSeconds >= 1){
                update();
                updates++;
                changeInSeconds--;
            }
            render();
            fps++;
             
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + fps + " Updates: " + updates);
                fps = 0;
                updates=0;
            }
        }
    }  
    
    public synchronized void start(String name){
      if(running) 
          return;
      running = true;
      thread = new Thread(this, name);
      thread.start();
    }

    public synchronized void stop(){
        if(!running) 
            return;
        
        running = false;
        
        try {
            frame.dispose();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /* public static void main(String args[]){
        Game game = new Game();
        game.start();
    }*/
    
    public State getGameState(){
        return gameState;
    }
    
    public KeyManager getKeyManager(){
        return keyManager;
    }
    
    public MouseManager getMouseManager(){
        return mouseManager;
    }
    
    public Camera getCamera(){
        return camera;
    }

    public JFrame getFrame() {
        return frame;
    }
  
    public State getMenuState() {
       return menuState;
    }
    public State getOptionState() {
        return optionState;
    }
    
    public State getHomeState() {
        return homeState;
    }
    
    public State getHowToPlay(){
        return howToPlay;
    }

    public State getLeaderBoard() {
        return leaderBoard;
    }

    public boolean isMusicActive() {
        return musicActive;
    }
    
    
}
