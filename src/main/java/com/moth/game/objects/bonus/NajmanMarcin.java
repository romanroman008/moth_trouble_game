package com.moth.game.objects.bonus;

import com.moth.game.Game;
import com.moth.game.enums.BonusType;
import com.moth.game.enums.ID;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;

import java.awt.*;

public class NajmanMarcin extends GameObject implements Bonus {

    public NajmanMarcin(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);
        this.height=70;
        this.width=40;
        this.velX=5;
        this.velY=5;
    }

    @Override
    public void tick() {
        tick(this);

    }

    @Override
    public void render(Graphics g) {
     //   render(g,this);
        g.drawImage(Game.najman_image,(int)x,(int)y,40,70,null);
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
        getHandler().getPlayer().turnOffKeys();
    }

    @Override
    public void bonusDepower() {

    }
}
