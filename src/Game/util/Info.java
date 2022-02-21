/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.util;

/**
 *
 * @author Elan Fernandez
 */
public class Info {
    
    private final String id, score, username;
    
    public Info(String id, String score, String username){
        this.id = id;
        this.score = score;
        this.username = username;
    }

    public int getId() {
        return Integer.parseInt(id);
    }

    public int getScore() {
        return Integer.parseInt(score);
    }
    
    @Override
    public String toString(){
        return username + " - Points: " + score;
    }
    
}
