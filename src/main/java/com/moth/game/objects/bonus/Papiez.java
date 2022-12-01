package com.moth.game.objects.bonus;

import com.moth.game.Game;
import com.moth.game.enums.BonusType;
import com.moth.game.enums.ID;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;

import java.awt.*;

public class Papiez extends GameObject implements Bonus {


    public Papiez(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);
        this.height = 50;
        this.width = 50;
        this.velX = 5;
        this.velY = 5;
    }

    @Override
    public void tick() {
        tick(this);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Game.kremowka_image, (int) x, (int) y, (int) width, (int) height, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) width, (int) width);
    }

    @Override
    public BonusType getBonusType() {
        return BonusType.PAPAJ;
    }

    @Override
    public void bonusPower() {

    }

    @Override
    public void bonusDepower() {

    }
}
