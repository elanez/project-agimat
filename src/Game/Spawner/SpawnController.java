/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Spawner;

import Game.Handler;
import Game.entity.EntityManager;

/**
 *
 * @author Kevin
 */
public class SpawnController {
    private Spawner spawner;
    private Handler handler;
    private int wave, maxWave;
    private int stage, maxStage;
    private int entityID;
    private EntityManager entityManager;

    public SpawnController(Handler handler, int maxStage, EntityManager entityManager) {
        this.handler = handler;
        
        this.maxStage = maxStage;
        this.entityManager = entityManager;
        spawner = new Spawner(handler, entityManager);
        stage = 0;
        entityID = 8;
        maxWave = 3;
        wave = 0;
    }
    
    public void update(){
       
        if(stage < maxStage){
            spawner.spawn(entityID, stage);
            spawner.update();
//            entityID++;
        }  
        if(spawner.getEnemiesToSpawn() >= spawner.getMAXENEMIES()){
            spawner.setEnemiesToSpawn(1);
            spawner.setEnemiesKilled(0);
            spawner.setEnemiesSpawned(0);
            
            stage++;
            entityID ++;
//            spawner.spawn(entityID);
//            spawner.update();
        }
    }

    public Spawner getSpawner() {
        return spawner;
    }
    
    
    
}
