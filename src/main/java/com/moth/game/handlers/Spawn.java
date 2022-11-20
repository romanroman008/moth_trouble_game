package com.moth.game.handlers;

import com.moth.game.Game;
import com.moth.game.HUD;
import com.moth.game.enums.ID;
import com.moth.game.handlers.BonusHandler;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;
import com.moth.game.objects.Player;
import com.moth.game.objects.bonus.*;
import com.moth.game.objects.enemies.Bat;
import com.moth.game.objects.enemies.Bulb;
import com.moth.game.objects.enemies.BulbMissile;

import java.util.*;

public class Spawn {
    private Handler handler;
    private BonusHandler bonusHandler;
    private HUD hud;
    private List<GameObject> bonusList;
    private Random r;
    private boolean darkKnight=false;
    private int scoreKeep;


    public Spawn(Handler handler,HUD hud){
        this.handler=handler;
        this.bonusHandler=this.handler.getBonusHandler();
        this.hud=hud;
        r=new Random();

        scoreKeep=0;

        bonusList=new ArrayList<>();                                                                     //creating list for the bonus draw
        bonusList.add(new MariuszPudzian(Game.WIDTH/2-100,Game.HEIGHT/2-100, ID.Bonus,handler));
        bonusList.add(new NajmanMarcin(Game.WIDTH/2-100,Game.HEIGHT/2-100, ID.Bonus,handler));
        bonusList.add(new MichaelJackson(Game.WIDTH/2-100,Game.HEIGHT/2-100, ID.Bonus,handler));
        bonusList.add(new Pizza(Game.WIDTH/2-100,Game.HEIGHT/2-100, ID.Bonus,handler));
        bonusList.add(new Papiez(Game.WIDTH/2-100,Game.HEIGHT/2-100, ID.Bonus,handler));
        bonusList.add(new Illuminati(Game.WIDTH/2-100,Game.HEIGHT/2-100, ID.Bonus,handler));
    }

    public void tick(){
        if(handler.getObjects().stream().noneMatch(o->o.getId()==ID.Bat)){
            scoreKeep++;
            if(r.nextInt(10)==0){
                handler.addObject(new BulbMissile(Game.WIDTH/2,50,ID.Enemy,5,handler));        //shooting light missile in random directions
            }
        }

//        if(handler.getObjects().stream().anyMatch(o->o.getId()==ID.Bat)) {
//            darkKnight = true;               //setting boolean that the bat is on the map
//
//        }





        if(hud.getScore()==1){
            handler.addObject(new Bulb(Game.WIDTH/2-35,0, ID.Bulb,handler));
            handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT-100, ID.Player,handler));
            //handler.addObject(bonusList.get(0));

        }

        if(scoreKeep%500==0&&scoreKeep!=0){
            //scoreKeep=0;
           // batTimer++;
            Collections.shuffle(bonusList);           //bonus list shuffle
            Collections.shuffle(bonusList);
          //  handler.removeObject(bonusList.get(0));
            bonusList.get(0).setX(-100);                     //resetting bonus position
            bonusList.get(0).setY(Game.HEIGHT/2-100);
            handler.addObject(bonusList.get(0));          //getting random bonus
        }

        if(scoreKeep==2000)
        {
            scoreKeep=0;
            handler.removeBonusesAndEnemies();                                            //clearing the map and summoning the bat
            handler.addObject(new Bat(-300,-300,4,handler,ID.Bat));
            handler.getBonusHandler().removeBonuses();
            darkKnight=true;       //setting boolean that the bat is on the map
        }
    }

    public void resetScore(){
        this.hud.resetScore();
        this.scoreKeep=0;
    }



}
