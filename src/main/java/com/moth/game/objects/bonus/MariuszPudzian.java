package com.moth.game.objects.bonus;

import com.moth.game.Game;
import com.moth.game.handlers.Handler;
import com.moth.game.enums.BonusType;
import com.moth.game.objects.GameObject;
import com.moth.game.enums.ID;

import java.awt.*;

public class MariuszPudzian extends GameObject implements Bonus {

    private BonusType bonusType=BonusType.PUDZIAN;

    public MariuszPudzian(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);
        this.height=32;
        this.width=32;
        this.velX=5;
        this.velY=5;
    }

    public MariuszPudzian(float x, float y, float height, float width, float velX, float velY, ID id) {
        super(x, y, height, width, velX, velY, id);
    }

    public MariuszPudzian(float x, float y, float height, float width, ID id) {
        super(x, y, height, width, id);
    }

    @Override
    public void tick() {
        tick(this);
        if(collision(this)){
            getHandler().getBonusHandler().addBonus(this);
            //  System.out.println("dotklo");
           // getHandler().removeObject(this);
        }

    }

    @Override
    public void render(Graphics g) {
        render(g,this);
       // bonusPower(g);
    }

    @Override
    public Rectangle getBounds() {
       return getBounds(this);
    }


    @Override
    public BonusType getBonusType() {
        return this.bonusType;
    }

    @Override
    public void bonusPower() {
        getHandler().getPlayer().setBonusSpeedMultiplier(4);
    }

    public void bonusDepower(){
        getHandler().getPlayer().setBonusSpeedMultiplier(0);
    }
}
