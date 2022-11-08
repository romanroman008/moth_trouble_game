package com.moth.game.objects.bonus;

import com.moth.game.Game;
import com.moth.game.enums.BonusType;
import com.moth.game.enums.ID;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;

import java.awt.*;

public class MichaelJackson extends GameObject  implements Bonus {
    float life;
    public MichaelJackson(float x, float y, ID id) {
        super(x, y, id);
    }

    public MichaelJackson(float x, float y, float height, float width, float velX, float velY, ID id) {
        super(x, y, height, width, velX, velY, id);
    }

    public MichaelJackson(float x, float y, float height, float width, ID id) {
        super(x, y, height, width, id);
    }

    public MichaelJackson(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);
        life=1f;
        this.height=70;
        this.width=30;
        this.velX=5;
        this.velY=5;
    }

    @Override
    public void tick() {
        life=tick(this,life);
    }

    @Override
    public void render(Graphics g) {
        //render(g,this);
//        g.setColor(Color.blue);
//        g.fillRect((int)x,(int) y,(int) width,(int)height);
        g.drawImage(Game.michael_jackson_image,(int)x,(int)y,30,70,null);
    }

    @Override
    public Rectangle getBounds() {
        return getBounds(this);
    }

    @Override
    public BonusType getBonusType() {
       return BonusType.MICHAEL_JACKSON;
    }

    @Override
    public void bonusPower() {
        getHandler().getPlayer().setBonusSpeedMultiplier(-1);
    }

    @Override
    public void bonusDepower() {
        getHandler().getPlayer().setBonusSpeedMultiplier(0);
    }
}
