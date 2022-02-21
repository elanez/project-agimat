/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.state;

import Game.Handler;
import Game.graphics.Assets;
import Game.graphics.Text;
import Game.ui.UIImageButton;
import Game.ui.UIManager;
import Game.util.Info;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Elan Fernandez
 */
public class LeaderBoard extends State{
    
    private ArrayList<Info> list, ranking;

    public LeaderBoard(Handler handler) {
        super(handler);
        list = new ArrayList<>();
        ranking = new ArrayList<>();
        uiManager = new UIManager(handler);
        
        uiManager.addObject(new UIImageButton(0,0,32,32,Assets.closeBtn, () -> {
            handler.getMouseManager().setUIManager(null);
            handler.getGame().getHomeState().activate();
        }));
    }

    @Override
    public void update() {
        uiManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.loginBg, 0, 0, handler.getWidth(), handler.getHeight(), null);
        uiManager.render(graphics);
        
        Text.drawString(graphics, "LeaderBoard", handler.getWidth()/4, handler.getHeight()/4 - 50, false, Color.black, Assets.font35);
        
        for(int i=0; i<ranking.size();i++){
            Text.drawString(graphics,Integer.toString(i+1) +". " + ranking.get(i).toString(), handler.getWidth()/4, handler.getHeight()/4 + (50 * i), false, Color.black, Assets.font35);
            if(i >= 3)
                break;
        }
    }
    
    private void loadScores(){
        list = new ArrayList<>();
        ranking = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/game", "root", "");
            Statement state = conDB.createStatement();
            ResultSet getData = state.executeQuery( "SELECT * FROM playerinfo " );
            Statement state2 = conDB.createStatement();
            ResultSet getName = state2.executeQuery("SELECT * FROM info_tbl");
            while(getData.next() && getName.next()){
                list.add(new Info(getData.getString("ID"),getData.getString("Score"), getName.getString("Username")));
            }
            
        } catch (SQLException | ClassNotFoundException ex ) {
            ex.printStackTrace();
        }  
    }
    
    private void sort(){
        int index=0;
        while(list.size() != 1){
            int i = 0;
            for(int j=1; j < list.size();j++){
                if(list.get(i).getScore() < list.get(j).getScore()){
                    i=j;
                    index = j;
                }
            }
            if(list.get(index).getId() == 1)
                list.remove(index);
            else{
                ranking.add(list.get(index));
                list.remove(index);
            }
        }
        if(list.get(0).getId() != 1)
            ranking.add(list.get(0));
    }
    
    @Override
    public void activate(){
        handler.getMouseManager().setUIManager(uiManager);
        setState(this);
        loadScores();
        sort();
    }
    
}
