package com.moth.game.objects.bonus;

import com.moth.game.enums.BonusType;
import com.moth.game.enums.ID;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;

import java.awt.*;

public class NajmanMarcin extends GameObject implements Bonus {
    public NajmanMarcin(float x, float y, ID id) {
        super(x, y, id);

    }

    public NajmanMarcin(float x, float y, float height, float width, float velX, float velY, ID id) {
        super(x, y, height, width, velX, velY, id);
    }

    public NajmanMarcin(float x, float y, float height, float width, ID id) {
        super(x, y, height, width, id);
    }

    public NajmanMarcin(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);
        this.height=32;
        this.width=32;
        this.velX=5;
        this.velY=5;
    }

    @Override
    public void tick() {
        tick(this);
        if(collision(this)){
            getHandler().getBonusHandler().addBonus(this);
            getHandler().removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        render(g,this);
    }

    @Override
    public Rectangle getBounds() {
       return getBounds(this);
    }

    @Override
    public BonusType getBonusType() {
        return BonusType.NAJMAN;
    }

    @Override
    public void bonusPower() {
        getHandler().getPlayer().setGotcha();
    }

    @Override
    public void bonusDepower() {

    }
}
