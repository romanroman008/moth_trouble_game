package com.moth.game.handlers;

import com.moth.game.Game;
import com.moth.game.enums.BonusType;
import com.moth.game.objects.GameObject;
import com.moth.game.objects.Player;
import com.moth.game.objects.bonus.Bonus;

import java.awt.*;
import java.util.HashMap;

public class BonusHandler {
    Game game;
    int counter=0;
    Handler handler;
    GameObject player;
    HashMap<Bonus,Integer> bonuses = new HashMap<>();

    public BonusHandler(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
        //player=handler.getPlayer();
    }

    public void tick(){
        if(!bonuses.entrySet().isEmpty()){
            bonusCountdown();
        }

    }
    public void render(Graphics g){
        //casy
        if(!bonuses.entrySet().isEmpty()){
            Font font = new Font("arial", 1, 30);
            bonuses.forEach((k,v)->{
                switch(k.getBonusType()){
                    case NAJMAN -> {
                        g.setColor(Color.orange);
                        g.setFont(font);
                        g.drawString("Najman bonuus maaan!",300,Game.HEIGHT/2);
                    }
                    case PUDZIAN -> {
                        g.setColor(Color.pink);
                        g.setFont(font);
                        g.drawString("POLSKA GUROM!1!1!",500,Game.HEIGHT/2);
                    }
                }
            });
        }

    }

    public void addBonus(Bonus bonus){
        this.bonuses.put(bonus,10);
        bonusAction(bonus);
    }
    private void bonusAction(Bonus bonus){
//        if(player==null) {               //bedzie problem imo
//            player= handler.getPlayer();
//        }
        bonus.bonusPower();
    }

    private void bonusCountdown(){
        counter++;
        if(counter>=100){
            counter=0;
            bonuses.forEach((k,v)->bonuses.put(k,v-1));
            //bonuses.forEach((x,y)->System.out.println(y));
            bonuses.forEach((k,v)->{
                if(v==0)
                    k.bonusDepower();
            });
            bonuses.entrySet().removeIf(b->b.getValue()==0);

        }
    }




}
