package com.moth.game.handlers;

import com.moth.game.Game;
import com.moth.game.HUD;
import com.moth.game.enums.ID;
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
    private int scoreKeep;
    private int bonusToGetIndex;


    public Spawn(Handler handler,HUD hud){
        this.handler=handler;
        this.bonusHandler=this.handler.getBonusHandler();
        this.hud=hud;
        r=new Random();

        scoreKeep=0;

        bonusList=new ArrayList<>();                                                                     //creating list for the bonus draw
        bonusList.add(new MariuszPudzian(Game.GAME_WIDTH /2-100,Game.GAME_HEIGHT /2-100, ID.Bonus,handler));
        bonusList.add(new NajmanMarcin(Game.GAME_WIDTH /2-100,Game.GAME_HEIGHT /2-100, ID.Bonus,handler));
        bonusList.add(new MichaelJackson(Game.GAME_WIDTH /2-100,Game.GAME_HEIGHT /2-100, ID.Bonus,handler));
        bonusList.add(new Pizza(Game.GAME_WIDTH /2-100,Game.GAME_HEIGHT /2-100, ID.Bonus,handler));
        bonusList.add(new Illuminati(Game.GAME_WIDTH /2-100,Game.GAME_HEIGHT /2-100, ID.Bonus,handler));
    }

    public void tick(){
        if(handler.getObjects().stream().noneMatch(o->o.getId()==ID.Bat)){
            scoreKeep++;
            if(r.nextInt(10)==0){
                handler.addObject(new BulbMissile(Game.GAME_WIDTH /2,50,ID.Enemy,5,handler));        //shooting light missile in random directions
            }
        }






        if(hud.getScore()==1){
            handler.addObject(new Bulb(Game.GAME_WIDTH /2-35,0, ID.Bulb,handler));                      //adding bulb and player
            handler.addObject(new Player(Game.GAME_WIDTH /2-32,Game.GAME_HEIGHT -100, ID.Player,handler));

        }

        if(scoreKeep%500==0&&scoreKeep!=0){

            bonusToGetIndex= r.nextInt(5);        //drawing bonus
            bonusList.get(bonusToGetIndex).setX(-100);                     //resetting bonus position
            bonusList.get(bonusToGetIndex).setY(Game.GAME_HEIGHT /2-100);
            handler.addObject(bonusList.get(bonusToGetIndex));          //getting random bonus
        }

        if(scoreKeep==2000)
        {
            scoreKeep=0;
            handler.removeBonusesAndEnemies();                                            //clearing the map and summoning the bat
            handler.addObject(new Bat(-300,-300,4,handler,ID.Bat));
            handler.getBonusHandler().removeBonuses();
        }
    }

    public void resetScore(){
        this.hud.resetScore();
        this.scoreKeep=0;
    }



}
