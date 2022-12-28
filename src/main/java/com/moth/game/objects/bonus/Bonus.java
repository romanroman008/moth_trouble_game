package com.moth.game.objects.bonus;

import com.moth.game.Game;
import com.moth.game.enums.BonusType;
import com.moth.game.enums.ID;
import com.moth.game.handlers.BonusHandler;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;

import java.awt.*;

public abstract class Bonus implements GameObject {
    protected double x, y, height, width, velX, velY;
    private BonusType bonusType;
    private ID id;
    private Handler handler;


    Bonus(double x, double y, double height, double width, double velX,double velY, BonusType bonusType) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.velX = velX;
        this.velY=velY;
        this.bonusType = bonusType;
        this.handler=Handler.getInstance();
        this.id = ID.Bonus;
    }



    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


    public void tick() {

        velY = Math.sin(x / 70) * 3;            //setting trajectory of bonus as sinusoid


        if (collision()) {
            BonusHandler.getInstance().addBonus(this);   //passing BonusInterface to BonusHandler
            handler.removeObject(this);                        // and removing it from game scene
        }

        x += velX;
        y += velY;

    }

    private void deleteBonusAfterEscapingScreenBounds() {
        if (x > Game.GAME_WIDTH)
            handler.removeObject(this);
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
    }

    private boolean collision() {                              //checking if player hit the bonus
        for (GameObject object : handler.getObjects()) {
            if (object.getId() == ID.Player) {
                if (getBounds().intersects(object.getBounds())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }

    public BonusType getBonusType() {
        return bonusType;
    }

    public ID getId() {
        return id;
    }

    public Handler getHandler() {
        return handler;
    }

    public abstract void bonusPower();

    public abstract void bonusDepower();
}
