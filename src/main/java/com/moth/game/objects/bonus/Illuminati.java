package com.moth.game.objects.bonus;

import com.moth.game.Game;
import com.moth.game.enums.BonusType;
import com.moth.game.enums.ID;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;

import java.awt.*;

public class Illuminati extends GameObject implements Bonus {
    public Illuminati(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);
        this.height=60;
        this.width=100;
        this.velX=5;
        this.velY=5;
    }

    @Override
    public void tick() {
        tick(this);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Game.jaszczur_image,(int)x,(int)y,(int)width,(int)height,null);
    }

    @Override
    public Rectangle getBounds() {
        return getBounds(this);
    }

    @Override
    public BonusType getBonusType() {
        return BonusType.ILLUMINATI;
    }

    @Override
    public void bonusPower() {

    }

    @Override
    public void bonusDepower() {

    }
}
