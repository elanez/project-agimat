/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Game.audio.MusicPlayer;
import Game.graphics.Camera;
import Game.state.State;
import Game.input.KeyManager;
import Game.input.MouseManager;
import Game.state.GameState;
import Game.state.MenuState;
import Game.world.World;

/**
 *
 * @author Elan Fernandez
 */
public class Handler {
    
    private Game game;
    private World world;
    
    public Handler(Game game){
        this.game = game;
    }
    
    public Camera getCamera(){
        return game.getCamera();
    }
    
    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }
    
    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }
    
    public int getWidth(){
        return game.getFrame().getWidth();
    }
    
    public int getHeight(){
        return game.getFrame().getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    
    public State getState(){
        return State.getState();
    }
    
    public MenuState getMenuState(){
        return (MenuState)game.getMenuState();
    }
    
    public GameState getGameState(){
        return (GameState)game.getGameState();
    }
    
    public MusicPlayer getMusicPlayer(){
        return new MusicPlayer();
    }
    
}
