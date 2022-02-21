/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.tiles;

import Game.Handler;
import java.util.ArrayList;

/**
 *
 * @author Elan Fernandez
 */
public class TileManager {

    private Handler handler;
    private ArrayList<Tile> tiles;
    
    public TileManager(Handler handler) {
        this.handler = handler;
        init();
    }
    
    public void init(){
       // grass, dirt, cliff, sand, water, plant, deepWater, plank, rock
        tiles = new ArrayList<>();
        tiles.add(new GrassTile(handler));  //0
        tiles.add(new DirtTile(handler));   //1
        tiles.add(new CliffTile(handler));  //2
        tiles.add(new SandTile(handler));   //3
        tiles.add(new WaterTile(handler));  //4
        tiles.add(new PlantTile(handler));  //5
        tiles.add(new DeepWaterTile(handler));//6
        tiles.add(new PlankTile(handler));  //7
        tiles.add(new RockTile(handler));   //8
        //tiles.add(new RoadTile(handler));   
        tiles.add(new PortalTile(handler)); //9
        tiles.add(new WaveLeftTile(handler));//10
        tiles.add(new WaveRightTile(handler));//11
        tiles.add(new WaveUpTile(handler));//12
        tiles.add(new WaveDownTile(handler));//13
        tiles.add(new PortalTile_1(handler)); //14
        tiles.add(new PortalTile_2(handler));//15
        tiles.add(new PortalTile_3(handler));//16
        
    }
      
    public Tile getTile(int id){
        return tiles.get(id);
    }

}
