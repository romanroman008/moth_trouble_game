package com.moth.game.objects;

import com.moth.game.Game;
import com.moth.game.enums.ID;
import com.moth.game.handlers.Handler;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class MenuBat extends GameObject {
    public MenuBat(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    @Override
    public void tick() {
        x+=-10;
        y+=8;

        if(x<-50)
            getHandler().removeObject(this);
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        g2d.rotate(Math.toRadians(-130), (int) x, (int) y);
        g.drawImage(Game.bat_image, (int) x, (int) y, 200, 100, null);
        g2d.setTransform(old);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 200, 100);
    }
}
