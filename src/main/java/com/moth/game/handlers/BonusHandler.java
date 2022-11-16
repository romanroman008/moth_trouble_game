package com.moth.game.handlers;

import com.moth.game.Game;
import com.moth.game.enums.BonusType;
import com.moth.game.objects.GameObject;
import com.moth.game.objects.Player;
import com.moth.game.objects.bonus.Bonus;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

public class BonusHandler {
    Game game;
    int counter=0;
    int trueCounter;
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
//        Font font = new Font("arial", 1, 20);
//        g.setColor(Color.orange);
//        g.setFont(font);
//        g.drawString("Weź sie rozpędź i jebnij łbem o ścianę",15,30);

        if(!bonuses.entrySet().isEmpty()){
            Font font = new Font("arial", 1, 20);
            bonuses.forEach((k,v)->{
                switch(k.getBonusType()){
                    case NAJMAN -> {
                        g.setColor(Color.BLUE);
                        g.setFont(font);
                        g.drawString("Weź sie rozpędź i jebnij łbem o ścianę",15,30);
                    }
                    case PUDZIAN -> {
                        g.setColor(Color.pink);
                        g.setFont(font);
                        g.drawString("POLSKA GUROM!1!1!",15,30);
                    }
                    case MICHAEL_JACKSON -> {
                        g.setColor(Color.WHITE);
                        g.setFont(font);
                        g.drawString("Moonwalk",15,30);

                    }
                    case PIZZA -> {
                        g.setColor(Color.ORANGE);
                        g.setFont(font);
                        g.drawString("Mamma mia, too much pizzerinia",15,30);

                    }
                }
            });
        }

    }

//    private void centerString(Graphics g, Rectangle r, String s, Font font) {
//        FontRenderContext frc =
//                new FontRenderContext(null, true, true);
//
//        Rectangle2D r2D = font.getStringBounds(s, frc);
//        int rWidth = (int) Math.round(r2D.getWidth());
//        int rHeight = (int) Math.round(r2D.getHeight());
//        int rX = (int) Math.round(r2D.getX());
//        int rY = (int) Math.round(r2D.getY());
//
//        int a = (r.width / 2) - (rWidth / 2) - rX;
//        int b = (r.height / 2) - (rHeight / 2) - rY;
//
//        g.setFont(font);
//        g.drawString(s, r.x + a, r.y + b);
//    }

    public void addBonus(Bonus bonus){
        this.bonuses.put(bonus,10);
        bonusAction(bonus);
    }
    private void bonusAction(Bonus bonus){
        bonus.bonusPower();
    }

    private void bonusCountdown(){
        counter++;
        if(counter>=30){
            counter=0;
            bonuses.forEach((k,v)->this.trueCounter=v);
            bonuses.forEach((k,v)->bonuses.put(k,v-1));
            //bonuses.forEach((x,y)->System.out.println(y));
            bonuses.forEach((k,v)->{
                if(v==0)
                    k.bonusDepower();
            });
            bonuses.entrySet().removeIf(b->b.getValue()==0);

        }
    }

    public void removeBonuses(){
        //bonuses.clear();
        bonuses.values().clear();
    }




}
