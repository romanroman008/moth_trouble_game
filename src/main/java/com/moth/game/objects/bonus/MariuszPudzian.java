package com.moth.game.objects.bonus;

import com.moth.game.Game;
import com.moth.game.handlers.Handler;
import com.moth.game.enums.BonusType;
import com.moth.game.objects.GameObject;
import com.moth.game.enums.ID;

import java.awt.*;

public class MariuszPudzian extends GameObject implements Bonus {

    float life;

    public MariuszPudzian(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);
        this.height=70;
        this.width=50;
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

    }

    @Override
    public void render(Graphics g) {
       // render(g,this);
        g.drawImage(Game.pudzian_image,(int)x,(int)y,50,70,null);
       // bonusPower(g);
    }

    @Override
    public Rectangle getBounds() {
       return getBounds(this);
    }


    @Override
    public BonusType getBonusType() {
        return BonusType.PUDZIAN;
    }

    @Override
    public void bonusPower() {
        getHandler().getPlayer().setBonusSpeedMultiplier(2);
    }

    public void bonusDepower(){
        getHandler().getPlayer().setBonusSpeedMultiplier(0);
    }

    public void resetBonusTime(){
        this.life=2f;
    }
}
