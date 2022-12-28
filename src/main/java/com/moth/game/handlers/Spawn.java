package com.moth.game.handlers;

import com.moth.game.Game;
import com.moth.game.HUD;
import com.moth.game.enums.ID;
import com.moth.game.objects.bonus.BonusFactory;
import com.moth.game.objects.Player;
import com.moth.game.objects.bonus.*;
import com.moth.game.objects.enemies.Bat;
import com.moth.game.objects.enemies.Bulb;
import com.moth.game.objects.enemies.BulbMissile;

import java.util.*;

public class Spawn {

    private static Spawn INSTANCE;

    private Handler handler;
    private BonusHandler bonusHandler;
    private HUD hud;
    private List<Bonus> bonusList;
    private Random r;
    private int scoreKeep;
    private int bonusToGetIndex;


    private Spawn() {
        this.handler = Handler.getInstance();
        this.bonusHandler = BonusHandler.getInstance();
        this.hud = HUD.getInstance();
        r = new Random();

        scoreKeep = 0;

        //bonusList = new ArrayList<>();
        bonusList= BonusFactory.createBonusList();
        //creating list for the bonus draw
//        bonusList.add(new MariuszPudzian(Game.GAME_WIDTH /2-100,Game.GAME_HEIGHT /2-100, BonusType.PUDZIAN, ID.Bonus,handler));
//        bonusList.add(new NajmanMarcin(Game.GAME_WIDTH /2-100,Game.GAME_HEIGHT /2-100,BonusType.NAJMAN, ID.Bonus,handler));
//        bonusList.add(new MichaelJackson(Game.GAME_WIDTH /2-100,Game.GAME_HEIGHT /2-100, BonusType.MICHAEL_JACKSON,ID.Bonus,handler));
//        bonusList.add(new Pizza(Game.GAME_WIDTH /2-100,Game.GAME_HEIGHT /2-100,BonusType.PIZZA, ID.Bonus,handler));
//        bonusList.add(new Illuminati(Game.GAME_WIDTH /2-100,Game.GAME_HEIGHT /2-100,BonusType.ILLUMINATI, ID.Bonus,handler));
    }

    public static Spawn getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Spawn();
        return INSTANCE;
    }

    public void tick() {
        if (handler.getObjects().stream().noneMatch(o -> o.getId() == ID.Bat)) {
            scoreKeep++;
            if (r.nextInt(10) == 0) {
                handler.addObject(new BulbMissile(Game.GAME_WIDTH / 2, 50, ID.Enemy, 5, handler));        //shooting light missile in random directions
            }
        }


        if (hud.getScore() == 1) {
            handler.addObject(new Bulb(Game.GAME_WIDTH / 2 - 35, 0, ID.Bulb, handler));                      //adding bulb and player
            handler.addObject(new Player(Game.GAME_WIDTH / 2 - 32, Game.GAME_HEIGHT - 100, ID.Player, handler));

        }

        if (scoreKeep % 500 == 0 && scoreKeep != 0) {

            bonusToGetIndex = r.nextInt(5);        //drawing bonus
            bonusList.get(bonusToGetIndex).setX(-100);                     //resetting bonus position
            bonusList.get(bonusToGetIndex).setY(Game.GAME_HEIGHT / 2 - 100);
            handler.addObject(bonusList.get(bonusToGetIndex));          //getting random bonus
        }

        if (scoreKeep == 2000) {
            scoreKeep = 0;
            handler.removeBonusesAndEnemies();                                            //clearing the map and summoning the bat
            handler.addObject(new Bat(-300, -300, 4, handler, ID.Bat));
            bonusHandler.removeBonuses();
        }
    }

    public void resetScore() {
        this.hud.resetScore();
        this.scoreKeep = 0;
    }


}
