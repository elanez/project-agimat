package Game.world;

import Game.Handler;
import Game.entity.creatures.Player;
import Game.tiles.TileManager;
import java.awt.Graphics;
import java.util.ArrayList;

public class WorldManager {
    
    private final Handler handler;
    private final Player player;
    private final TileManager tile;
    private ArrayList<World> worlds;
    
    public WorldManager(Handler handler, Player player, TileManager tileManager){
        this.handler = handler;
        this.player = player;
        tile = tileManager;
        worlds = new ArrayList<>();
        
        init();
    }
    
    private void init(){
        worlds.add(new World(handler, "src\\res\\worlds\\world1.txt", "src\\res\\entities\\startingEntities.txt", player, tile, "Nayon"));
        worlds.add(new World(handler, "src\\res\\worlds\\stage1.txt", "src\\res\\entities\\entities1.txt", player, tile, "Dalampasigan"));
        worlds.add(new World(handler, "src\\res\\worlds\\stage2.txt", "src\\res\\entities\\entities2.txt", player, tile, "Kapatagan"));
        worlds.add(new World(handler, "src\\res\\worlds\\stage3.txt", "src\\res\\entities\\entities3.txt", player, tile, "Nayon2"));
        worlds.add(new World(handler, "src\\res\\worlds\\stage4.txt", "src\\res\\entities\\entities4.txt", player, tile, "Dalampasigan2"));
        worlds.add(new World(handler, "src\\res\\worlds\\stage5.txt", "src\\res\\entities\\entities5.txt", player, tile, "Nayon3"));
        
        handler.setWorld(worlds.get(0));
    }
    
    public void reset(){
        worlds = new ArrayList<>();
        init();
        
    }
    
    public void update(){
        handler.getWorld().update();
    }
    
    public void render(Graphics graphics){
        handler.getWorld().render(graphics);
    }

    public ArrayList<World> getWorlds() {
        return worlds;
    }
       
}
