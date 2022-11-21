package com.moth.game.objects;

import com.moth.game.Game;
import com.moth.game.enums.ID;
import com.moth.game.handlers.Handler;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class MenuMoth extends GameObject {
    public boolean thisIsTheEnd;   //confirm that this is end screen
    public boolean falling;      //confirm that moth is falling
    int i=0;
    int j=0;

    public MenuMoth(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);
        velX = 1;
        thisIsTheEnd=false;
        falling=false;
    }


    @Override
    public void tick() {

        if (thisIsTheEnd) {

            startFalling();

            if (!falling) {
                x += (-1);
                y += (-1);
            }


            if (falling) {
                y += 5;
                if(collision())
                    getHandler().removeObject(this);
            }

        } else {
            y = -(x * x) / 180 + 100;
            x -= velX;
            if (x <= 0)
                velX = velX * (-1);

            if (y < 10 && (x > Math.sqrt((double) -180 * (y - 100))))
                velX *= -1;
        }


    }

    private void startFalling() {
        if (y == 50) {
            falling=true;
            getHandler().addObject(new MenuBat(Game.WIDTH/2+50,-50,ID.MenuBat,getHandler()));
        }
    }
    private boolean collision() {
        for (GameObject object : getHandler().getObjects()) {
            if (object.getId() == ID.MenuBat) {
                if (getBounds().intersects(object.getBounds())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void render(Graphics g) {

        if(!falling)
        g.drawImage(Game.moth_image, (int) x, (int) y, 35, 25, null);

        if (falling) {

            Graphics2D g2d = (Graphics2D) g;
            AffineTransform old = g2d.getTransform();
            g2d.rotate(Math.toRadians(j), (int) x, (int) y);
            g2d.drawImage(Game.moth_image, (int) x-17, (int) y-13, 35, 25, null);
            g2d.setTransform(old);
            i++;
            if(i%5==0)
                j++;
        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 70, 70);
    }
}
