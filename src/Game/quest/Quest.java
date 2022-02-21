package Game.quest;

import Game.Handler;
import Game.graphics.Assets;
import Game.graphics.Text;
import Game.items.Item;
import Game.tiles.Tile;
import Game.ui.UIImageButton;
import Game.ui.UIManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Quest {
    
    private final Handler handler;
    private boolean active, ongoing, displayText, defend;
    private UIManager uiManager;
    
    private final int btnX, btnY, btnW, btnH;
    private final int questX, questY, questW, questH, msgX, msgY;
    private String objective, msg, type, description, msgReward;
    private int id, currentNo, total, rewardType, rewardCount, currentQuest;
    private int questID, score;
    private final int totalQuest;
    
    public Quest(Handler handler){
        this.handler = handler;
        active = false;
        
        questX = 20;
        questY = 20;
        questW = handler.getWidth() - questX*2;
        questH = handler.getHeight() - questY*2;
        msgX = handler.getWidth() - 128;
        msgY = 32;
        
        btnX = questW/4;
        btnY = questH/3;
        btnW = 100;
        btnH = 120;
        
        defend = false;
        displayText = false;        
        currentQuest = 1;
        description = "";
        
        init();
        reset();
        
        totalQuest = uiManager.getObjects().size()-3;
    }
    
    private void init(){
        uiManager = new UIManager(handler);
        
        uiManager.addObject(new UIImageButton(questX + questW - 32,questY, 32, 32,Assets.closeBtn, () -> {
            deactivate();
        }));
        
        uiManager.addObject(new UIImageButton(questX + questW/2 - 32,questY + questH - 64, 64, 64,Assets.acceptBtn, () -> {
            questlog(questID);
            uiManager.getObjects().get(2).setActive(true);
        }));
        uiManager.getObjects().get(1).setActive(false);
        
        uiManager.addObject(new UIImageButton(questX + questW/2 - 32,questY + 80, 64, 54,Assets.cancelBtn, () -> {
            if(ongoing){
                reset();
                uiManager.getObjects().get(2).setActive(false);
            }
        }));
        if(!ongoing){
            uiManager.getObjects().get(2).setActive(false);
        }
        
        uiManager.addObject(new UIImageButton(btnX,btnY, btnW, btnH,Assets.questPig, () -> {
            questID = 0;
            description = "Kill 7 pigs";
            msgReward = "Reward: 1 Fire Agimat!";
            displayText = true;
            uiManager.getObjects().get(1).setActive(true);
        }));
        
        uiManager.addObject(new UIImageButton(questW*3/8,btnY, btnW, btnH,Assets.questMananangal, () -> {
            questID = 1;
            description = "Kill 5 Manananggals";
            msgReward = "Reward: 1 Wind Agimat";
            displayText = true;
            uiManager.getObjects().get(1).setActive(true);
        }));
        
        uiManager.addObject(new UIImageButton(questW/2,btnY, btnW, btnH,Assets.questAswang, () -> {
            questID = 2;
            description = "Kill 3 Aswang";
            msgReward = "Reward: 1 Lightning!";
            displayText = true;
            uiManager.getObjects().get(1).setActive(true);
        }));
        
        uiManager.addObject(new UIImageButton(questW*5/8,btnY, btnW, btnH,Assets.questBossMananagal, () -> {
            questID = 3;
            description = "Defeat the boss";
            msgReward = "Reward: 10000 gold!";
            displayText = true;
            uiManager.getObjects().get(1).setActive(true);
        }));
        
        uiManager.addObject(new UIImageButton(questW*6/8,btnY, btnW, btnH,Assets.questDayuhan, () -> {
            questID = 4;
            description = "Bonus Quest";
            msgReward = "Reward: 10000 gold!";
            displayText = true;
            uiManager.getObjects().get(1).setActive(true);
        }));
        
        for(int i=currentQuest+3; i<uiManager.getObjects().size();i++){
            uiManager.getObjects().get(i).setActive(false);
        }
    }
    
    private void questlog(int id){
        switch(id){
            case 0:
                if(currentQuest == 1)
                    setKillQuest("kill pigs",7,7,2,1,100);
                else
                    setKillQuest("kill pigs",7,7,1,500,100);
                defend = false;
                break;
            case 1:
                if(currentQuest == 2)
                    setKillQuest("kill mananaggal",5,8,2,2,250);
                else
                    setKillQuest("kill mananaggal",5,8,1,1000,250);
                defend = false;
                break;
            case 2:
                if(currentQuest == 3)
                    setKillQuest("kill aswang",3,9,2,3,700);
                else
                    setKillQuest("kill aswang",3,9,2,3,700);
                defend = false;
                break;
            case 3:
                setKillQuest("boss fight",1,10,1,10000,1000);
                defend = false;
                break;
            case 4:
                setKillQuest("Defend the Village",30,15,1,10000,1500);
                handler.setWorld(handler.getState().getWorldManager().getWorlds().get(4));
                handler.getWorld().getPlayer().setX(24 * Tile.TILE_WIDTH);
                handler.getWorld().getPlayer().setY(24* Tile.TILE_HEIGHT);
                handler.getMouseManager().setWorld(handler.getWorld());
                deactivate();
                break;
//            case 5:
//                setKillQuest("Defend the Village",1,15,1,10000,1500);
//                handler.setWorld(handler.getState().getWorldManager().getWorlds().get(5));
//                handler.getWorld().getPlayer().setX(25 * Tile.TILE_WIDTH);
//                handler.getWorld().getPlayer().setY(10* Tile.TILE_HEIGHT);
//                handler.getMouseManager().setWorld(handler.getWorld());
        }
    }
    
    public void update(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER))
            deactivate();
        
        if(ongoing){
            checkQuest();
        }
            
        if(active){
            uiManager.update();
        }
    }
    
    public void render(Graphics graphics){
        if(active){
            graphics.drawImage(Assets.ShopInventoryP, questX, questY,questW,questH, null);
            uiManager.render(graphics);
            if(ongoing){
                Text.drawString(graphics, "Quest ongoing", questX + questW/2,questY + 64, true, Color.yellow, Assets.font28);
            }
            renderDescription(graphics);
        }else{
            graphics.setColor(Color.WHITE);
            graphics.fillRect(handler.getWidth()-256,0,256,64);
            Text.drawString(graphics, objective, msgX, msgY, true, Color.BLACK, Assets.font16);
        
        }
    }
    
    public void renderDescription(Graphics graphics){
        if(displayText){
            Text.drawString(graphics, description,  questX + questW/2,questY + questH - 192, true, Color.white, Assets.font35);
            Text.drawString(graphics, msgReward,  questX + questW/2,questY + questH - 128, true, Color.yellow, Assets.font35);
        }
    }
    
    private void checkQuest(){
        if(ongoing){
            objective = msg + " " + currentNo + "/" + total;
            if(currentNo >= total){
                questComplete();
            }    
        }
    }
    
    private void nextQuest(){
        reset();
        questlog(5);
    }
    
    public void setKillQuest(String msg, int total, int entityID, int rewardType, int rewardCount, int score){
        if(!ongoing){
            this.total = total;
            this.id = entityID;
            this.rewardType = rewardType;
            this.rewardCount = rewardCount;  
            this.msg = msg;
            this.objective = msg + " " + currentNo + "/" + total;
            this.score = score;
            type = "kill";
            ongoing = true;
        }
    }
    
    public void setBonusQuest(String msg, int total, int entityID, int rewardType, int rewardCount, int score){
        if(!ongoing){
            this.total = total;
            this.id = entityID;
            this.rewardType = rewardType;
            this.rewardCount = rewardCount;  
            this.msg = msg;
            this.objective = msg + " " + currentNo + "/" + total;
            this.score = score;
            type = "bonus";
            ongoing = true;
        }
    }
    
    public void setDeliverQuest(String msg, int total, int itemID, int rewardType, int rewardCount, int score){
        if(!ongoing){
            this.total = total;
            this.id = itemID;
            this.rewardType = rewardType;
            this.rewardCount = rewardCount;            
            this.msg = msg;
            this.objective = msg + " " + currentNo + "/" + total;
            this.score = score;
            type = "deliver";
            ongoing = true;
        }
        
        ArrayList<Item> items = handler.getWorld().getPlayer().getInventory().getInventoryItems();
        for(int i=0; i<items.size();i++){
            if(items.get(i).getID() == id){
                currentNo = items.get(i).getCount();
            }
        }
    }
    
    private void questComplete(){
        if(type.equals("deliver")){
            ArrayList<Item> items = handler.getWorld().getPlayer().getInventory().getInventoryItems();
            for(int i=0; i<items.size();i++){
            if(items.get(i).getID() == id){
                handler.getWorld().getPlayer().getInventory().remove(Item.getItem(id), total);
            }
        }
        }else if(questID == 4){
            currentQuest++;
            defend = false;
//            if(currentQuest == 4)
//                nextQuest();
            handler.getGameState().getWorldManager().reset();
            handler.setWorld(handler.getState().getWorldManager().getWorlds().get(0));
            handler.getWorld().getPlayer().setX(25 * Tile.TILE_WIDTH);
            handler.getWorld().getPlayer().setY(10* Tile.TILE_HEIGHT);
            handler.getMouseManager().setWorld(handler.getWorld());
            handler.getWorld().getPlayer().getDialog().setDialog("Quest Complete","You have successfully defended the village");
            handler.getWorld().getPlayer().getDialog().activeSwitch();
            
            reset();
            return;
        }
        reward(rewardType, rewardCount);
        uiManager.getObjects().get(currentQuest).setActive(true);
        currentQuest++;
        reset();
    }
        
    
    public void reset(){
        objective = "No current quest";
        type = "";
        ongoing = false;
        currentNo = 0;
        score = 0;
        total = 0;
        questID=0;
        description = "";
    }
    
    private void reward(int type, int count){
        switch(type){
            case 1://gold rewardType
                handler.getWorld().getPlayer().setPlayerMoney(handler.getWorld().getPlayer().getPlayerMoney() + count);
//                handler.getWorld().getPlayer().setPlayerAgimat(1);
                if(currentQuest < totalQuest)
                    handler.getWorld().getPlayer().getDialog().setDialog("Quest complete!", "You have obtained " + rewardCount + " gold" + " New Quest Unlocked!");
                else
                    handler.getWorld().getPlayer().getDialog().setDialog("Quest complete!", "You have obtained " + rewardCount + " gold");
                break;
            case 2://item rewardType
                handler.getWorld().getPlayer().setPlayerAgimat(count);
//                handler.getWorld().getPlayer().setPlayerAgimat(2);
                if(currentQuest < totalQuest)
                    handler.getWorld().getPlayer().getDialog().setDialog("Quest complete!", "You have obtained " + msgReward + " New Quest Unlocked!");
                else
                    handler.getWorld().getPlayer().getDialog().setDialog("Quest complete!",msgReward );
                break;
        }
        handler.getWorld().getPlayer().changeScore(score);
        handler.getWorld().getPlayer().getDialog().activeSwitch();
    }
    
    public void activate(){
        active = true;
        init();
        handler.getMouseManager().setUIManager(uiManager);
        handler.getWorld().getPlayer().setPots(false);
    }
    
    public void deactivate(){
        active = false;
        uiManager.getObjects().get(1).setActive(false);
        handler.getMouseManager().setUIManager(null);
        handler.getWorld().getPlayer().setPots(true);
        displayText = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentNo() {
        return currentNo;
    }

    public void setCurrentNo(int currentNo) {
        this.currentNo = currentNo;
    }

    public boolean isOngoing() {
        return ongoing;
    }

    public int getCurrentQuest() {
        return currentQuest;
    }

    public void setCurrentQuest(int currentQuest) {
        this.currentQuest = currentQuest;
    }

    public boolean isActive() {
        return active;
    }

    public String getType() {
        return type;
    }
}
