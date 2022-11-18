package com.moth.game.objects.bonus;

import com.moth.game.Game;
import com.moth.game.enums.BonusType;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;
import com.moth.game.enums.ID;

import java.awt.*;

public interface Bonus {

    default void tick(GameObject gameObject){

        gameObject.setVelY((float) Math.sin(gameObject.getX()/70)*3);


        if(collision(gameObject)){
            gameObject.getHandler().getBonusHandler().addBonus((Bonus)gameObject);
            gameObject.getHandler().removeObject(gameObject);
        }

        gameObject.setX(gameObject.getX()+gameObject.getVelX());
        gameObject.setY(gameObject.getY()+gameObject.getVelY());


        if(gameObject.getX()>Game.WIDTH)
               gameObject.getHandler().removeObject(gameObject);


    }

    default void render(Graphics g,GameObject o) {
        g.setColor(Color.green);
        g.fillRect((int)o.getX(),(int) o.getY(),(int) o.getWidth(),(int)o.getHeight());
    }

    default boolean collision(GameObject gameObject){
        for (GameObject object : gameObject.getHandler().getObjects()) {
            if(object.getId()== ID.Player){
                if(getBounds(gameObject).intersects(object.getBounds())) {
                    return true;
                }
            }
        }
        return false;
    }

    default Rectangle getBounds(GameObject o) {
        return new Rectangle((int)o.getX(),(int) o.getY(),(int) o.getWidth(),(int)o.getHeight());
    }

    BonusType getBonusType();


    void bonusPower();
    void bonusDepower();
}
