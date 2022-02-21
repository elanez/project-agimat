package Game.state;

import Game.Handler;
import Game.entity.creatures.Player;
import Game.tiles.Tile;
import Game.tiles.TileManager;
import Game.world.WorldManager;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class GameState extends State{

    private Player player;
    private TileManager tileManager;
    private String loginID;
    
    private final Thread musicThread;
    
    public GameState(Handler handler, String loginID){
        super(handler);
        this.loginID = loginID;
        tileManager = new TileManager(handler);
        player = new Player(handler, 25 * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT, loginID, true);
        worldManager = new WorldManager(handler, player, tileManager);
        musicThread = new Thread(handler.getMusicPlayer(),"..");
//        musicThread.start();
    }

    public void restart(){
        tileManager = new TileManager(handler);
        player = new Player(handler, 25 * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT, this.loginID, false);
        worldManager = new WorldManager(handler, player, tileManager);
    }
    
    
    public void load(){
        tileManager = new TileManager(handler);
        player = new Player(handler, 25 * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT, this.loginID, true);
        worldManager = new WorldManager(handler, player, tileManager);
    }
    
    
    @Override
    public void update(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
            handler.getGame().getOptionState().activate();
            State.setState(handler.getGame().getOptionState());
        }
        
        worldManager.update();
    }
    
    @Override
    public void render(Graphics graphics){
        worldManager.render(graphics);
    }

    public Player getPlayer() {
        return player;
    }
    
    
}
