/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Spawner;
import Game.entity.EntityManager;
import Game.Handler;
import Game.entity.Entity;
import java.util.Random;
/**
 *
 * @author Kevin
 */
public class Spawner {
    private int enemiesKilled;
    private int enemiesToSpawn;
    private int enemiesSpawned;
    private int x, y, location;
    private int entityID, entityX, entityY;
    private int spawnerX, spawnerY;
    private final int MAXENEMIES = 4;
    private EntityManager entityManager;
    private Handler handler;
    private Random random;

    public Spawner(Handler handler, EntityManager entityManager) {
        this.handler = handler;
        this.entityManager = entityManager;
        random = new Random();
        enemiesKilled = 0;
        enemiesToSpawn = 1;
        enemiesSpawned = 0;
        
        entityX = 5;
        entityY = 5;
        
    }
    public void spawn(int entityID, int stage){
        this.entityID = entityID;
        if(enemiesSpawned == 0){
           for(enemiesSpawned = 0; enemiesSpawned < enemiesToSpawn; enemiesSpawned++)
            {
                if(enemiesToSpawn < MAXENEMIES){
                    location = random.nextInt(4)+ 1;
                    placer(location);
                    System.out.println("spawn");
                    for(Entity e : handler.getWorld().getEntityManager().getEntities()){
                        if(entityX == e.getX()|| entityY == e.getY()){
                            entityX += 2;
                            entityY += 2;
                        }
                    }
                    entityManager.addEntity(entityID, entityX, entityY);
//                 entityY++;
                    if(stage == 3)
                        break;
                }
                
            } 
        }
        String str = entityID+"," + entityX + ","+entityY;
        
//        for(String temp: handler.getWorld().getPlacedEntities()){
//            String[] tokens = temp.split(",");
//            if(Integer.parseInt(tokens[1]) == entityX && Integer.parseInt(tokens[2]) == entityY)
//            {
//                handler.getWorld().getPlacedEntities().set(handler.getWorld().getPlacedEntities().indexOf(temp), str);
//                return;
//            }
//        }
//        handler.getWorld().getPlacedEntities().add(str);
    }
    private void placer(int location){
        
        switch(location){
            case 0:
                setEntityX(5);
                setEntityY(random.nextInt(20) + 1);
                break;
            case 1:
                setEntityX(15);
                setEntityY(random.nextInt(20) + 1);
                break;
            case 2:
                setEntityX(random.nextInt(20) + 1);
                setEntityY(5);
                break;
            default:
                setEntityX(random.nextInt(20) + 1);
                setEntityY(15);
                break;
        }
    }
    public void update(){
        if(enemiesKilled == enemiesToSpawn){
            enemiesSpawned = 0;
            enemiesKilled = 0;
            enemiesToSpawn++;
            System.out.println("reset");
        }
        
    }

    public void setEnemiesSpawned(int enemiesSpawned) {
        this.enemiesSpawned = enemiesSpawned;
    }

    
    public int getEnemiesToSpawn() {
        return enemiesToSpawn;
    }

    public int getMAXENEMIES() {
        return MAXENEMIES;
    }

    public void setEnemiesToSpawn(int enemiesToSpawn) {
        this.enemiesToSpawn = enemiesToSpawn;
    }
    
    public int getEntityID() {
        return entityID;
    }
    
    
    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public void setEntityX(int entityX) {
        this.entityX = entityX;
    }

    public void setEntityY(int entityY) {
        this.entityY = entityY;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }
    
    
}
