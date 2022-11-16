package com.moth.game;

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

    private int scoreKeep=0;

    public Spawn(Handler handler,HUD hud){
        this.handler=handler;
        this.bonusHandler=this.handler.getBonusHandler();
        this.hud=hud;
        r=new Random();
        bonusList=new ArrayList<>();

        bonusList.add(new MariuszPudzian(Game.WIDTH/2-32,Game.HEIGHT/2-100, ID.Bonus,handler));
        bonusList.add(new NajmanMarcin(Game.WIDTH-100,Game.HEIGHT/2-100, ID.Bonus,handler));
        bonusList.add(new MichaelJackson(Game.WIDTH-200,Game.HEIGHT/2-100, ID.Bonus,handler));
        bonusList.add(new Pizza(Game.WIDTH-200,Game.HEIGHT/2-100, ID.Bonus,handler));
    }

    public void tick(){
        if(handler.getObjects().stream().noneMatch(o->o.getId()==ID.Bat)){
            scoreKeep++;
            if(r.nextInt(10)==0){
                handler.addObject(new BulbMissile(Game.WIDTH/2,50,ID.Enemy,5,handler));
            }
        }

        if(handler.getObjects().stream().anyMatch(o->o.getId()==ID.Bat)) {
//        else{
//            if(handler.getObjects().stream().anyMatch(o->o.getId()==ID.Bat))
//            darkKnight=false;
            darkKnight = true;
//        }

        }





        if(hud.getScore()==1){
            handler.addObject(new Bulb(Game.WIDTH/2-35,0, ID.Bulb,handler));
            handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2, ID.Player,handler));
                      //  handler.addObject(new Bat(-300,-300,ID.Enemy));

//            handler.addObject(new MariuszPudzian(Game.WIDTH/2-32,Game.HEIGHT/2-100, ID.Bonus,handler));
//            handler.addObject(new NajmanMarcin(Game.WIDTH-100,Game.HEIGHT/2-100, ID.Bonus,handler));
//            handler.addObject(new MichaelJackson(Game.WIDTH-200,Game.HEIGHT/2-100, ID.Bonus,handler));
        }

        if(scoreKeep%500==0&&scoreKeep!=0){
            //scoreKeep=0;
           // batTimer++;
            Collections.shuffle(bonusList);
            handler.removeObject(bonusList.get(0));
            bonusList.get(0).setX(1);
            handler.addObject(bonusList.get(0));
        }

       // if(hud.getScore()==100)
        if(scoreKeep==1000)
        {
            scoreKeep=0;
            handler.removeBonusesAndEnemies();
            handler.addObject(new Bat(-300,-300,4,handler,ID.Bat));
            handler.getBonusHandler().removeBonuses();
            darkKnight=true;
        }
    }



}
