package Game.state;

import Game.Handler;
import Game.graphics.Assets;
import Game.ui.UIImageButton;
import Game.ui.UIManager;
import Game.datahelper.DBconnect;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class OptionState extends State{
    private int tempHealth,tempStamina,tempPlayerMoney,tempInventory,tempInventoryCount,tempQuest, tempDamage,tempScore,tempCount,tempPlayerAgimat;
    private String tempEquip;
    private String loginID;
    
    public OptionState(Handler handler, String loginID) {
        super(handler);
        this.loginID = loginID;
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        init();
    }
    
    private void init(){
        uiManager.addObject(new UIImageButton(300,250,150,70,Assets.resumeBtn, () -> {
            handler.getGameState().activate();
        }));
        
        uiManager.addObject(new UIImageButton(300,350,150,70,Assets.saveBtn, () -> {
            savePlayer();
            
        }));
        
        uiManager.addObject(new UIImageButton(300,450,150,70,Assets.exitBtn, () -> {
            handler.getMouseManager().setUIManager(null);
            handler.getGame().getHomeState().activate();
        }));
    }

    @Override
    public void update() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE))
            handler.getGameState().activate();
        
        uiManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.signupBg,0,0,handler.getWidth(),handler.getHeight(),null);
        uiManager.render(graphics);
    }
    private void savePlayer(){
            tempHealth = (int) handler.getWorld().getPlayer().getHealth();
            tempStamina = (int) handler.getWorld().getPlayer().getStamina();
            tempEquip = handler.getWorld().getPlayer().getInventory().getEquipID()+ ","+ handler.getWorld().getPlayer().getCount();
            tempPlayerMoney = handler.getWorld().getPlayer().getPlayerMoney();
            tempInventory = 0;
            tempQuest = handler.getWorld().getPlayer().getQuest().getCurrentQuest();
            tempDamage = handler.getWorld().getPlayer().getDamage();
            tempScore = handler.getWorld().getPlayer().getPlayerScore();
            tempCount = handler.getWorld().getPlayer().getPlayerAgimat();
        try {
            /*PrintWriter out = new PrintWriter("src\\res\\invent\\playerStats.txt");
                out.print(tempHealth);
                out.print(":");
                out.print(tempStamina);
                out.print(":");
                out.print(tempEquip);
                out.print(":");
                out.print(tempPlayerMoney);
                out.println(":");
                for(int i = 0; i < handler.getWorld().getPlayer().getInventory().getInventoryItems().size();i++){
                    tempInventory = handler.getWorld().getPlayer().getInventory().getInventoryItems().get(i).getID();
                    tempInventoryCount = handler.getWorld().getPlayer().getInventory().getInventoryItems().get(i).getCount();
                    out.print(tempInventory);
                    out.print("-");
                    out.print(tempInventoryCount);
                    out.print(",");
            out.close();*/
            String inventory = "";
            for(int i = 0; i < handler.getWorld().getPlayer().getInventory().getInventoryItems().size();i++){
                tempInventory = handler.getWorld().getPlayer().getInventory().getInventoryItems().get(i).getID();
                tempInventoryCount = handler.getWorld().getPlayer().getInventory().getInventoryItems().get(i).getCount();
                inventory += tempInventory + "-" + tempInventoryCount + ",";
            }
            DBconnect connect = new DBconnect();
            connect.putdata("UPDATE playerinfo SET Gold= '"+tempPlayerMoney+"',Equipped= '"+tempEquip+"', Inventory= '"+inventory
                                                    +"', QuestLog= '"+tempQuest+"', Health= '"+tempHealth+"', Stamina= '"+tempStamina
                                                    +"', Damage= '"+tempDamage+"', Score = '"+tempScore+"',Count = '"+tempCount+"' WHERE ID = '"+loginID+"'");
                                                      
            System.out.println("SAVE");
            JOptionPane.showMessageDialog(null,"Game Saved!");
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
