package com.moth.game.objects;

import com.moth.game.Game;
import com.moth.game.enums.ID;

import java.awt.*;

public class MenuParticle extends GameObject{
    public MenuParticle(float x, float y, ID id) {
        super(x, y, id);
        velX=1;
    }

    @Override
    public void tick() {




        y=-(x*x)/180+100;
        x-=velX;
        if(x<=0)
            velX=velX*(-1);

        if(y<10&&(x>Math.sqrt((double)-180*(y-100))))
            velX*=-1;

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Game.moth_image,(int)x,(int)y,35,25,null);

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
