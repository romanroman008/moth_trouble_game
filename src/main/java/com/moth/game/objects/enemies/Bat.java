package com.moth.game.objects.enemies;

import com.moth.game.Game;
import com.moth.game.enums.ID;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.Console;
import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.Math.atan;
import static java.lang.Math.toRadians;

public class Bat extends GameObject {
    Random r;
    int corner;
    float destX;
    float destY;
    private Handler handler;
    float a;
    float b;
    private int passageAmount;
    private float diffX;
    private float diffY;
    private double angle;
    private int coin;
    private double tg;


    public Bat(float x, float y, ID id) {
        super(x, y, id);
        r = new Random();
        corner = 0;
        velY = -2;
    }

    public Bat(float x, float y, int passageAmount, Handler handler, ID id) {
        super(x, y, id);
        this.passageAmount = passageAmount + 1;
        this.handler = handler;
        r = new Random();
        corner = 0;
        velY = -2;
    }

    public Bat(float x, float y, float height, float width, float velX, float velY, ID id) {
        super(x, y, height, width, velX, velY, id);
    }

    public Bat(float x, float y, float height, float width, ID id) {
        super(x, y, height, width, id);
    }

    public Bat(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);
    }


    @Override
    public void tick() {
        if (x <= -200 || x >= 1480 || y <= -200 || y >= 1200) {

            if (corner <= 3 && passageAmount > 0) {
                coordinatesRandomizer();
                corner++;
                passageAmount--;
            } else
                corner = 0;


            if (passageAmount == 0) {
                handler.removeBat();
            }


        }

        x += velX;
        y += velY;

    }

    private boolean throwACoin() {
        if (r.nextInt(2) == 1)
            return true;
        return false;

    }


    private void coordinatesRandomizer() {
        switch (corner) {
            case 0 -> {
                x = 1;                              //left upper corner
                y = 1;
                if (throwACoin()) {
                    destX = 1300;
                    destY = r.nextInt(500, 1000);
                } else {
                    destX = r.nextInt(650, 1300);
                    destY = 1000;
                }

                tg = abs(destY) / abs(destX);
                angle = Math.toRadians(90) + atan(tg);

            }
            case 1 -> {
                x = 1280;                       //right bottom corner
                y = 960;

                if (throwACoin()) {
                    destX = -10;
                    destY = r.nextInt(1, 500);
                } else {
                    destX = r.nextInt(1, 650);
                    destY = -10;
                }
                tg = abs(x) / abs(Game.HEIGHT - destY);
                angle = -atan(tg);
            }
            case 2 -> {
                x = 1;                             //left bottom corner
                y = 960;

                if (throwACoin()) {
                    destX = 1300;
                    destY = r.nextInt(1, 500);
                } else {
                    destX = r.nextInt(650, 1300);
                    destY = -10;
                }

                tg = abs(destX) / abs(Game.HEIGHT - destY);
                angle = atan(tg);
            }
            case 3 -> {
                x = 1280;                                           //right upper corner
                y = -20;

                if (throwACoin()) {
                    destX = -10;
                    destY = r.nextInt(500, 1000);
                } else {
                    destX = r.nextInt(1, 650);
                    destY = 1000;
                }
                tg = abs(destY) / abs(Game.WIDTH - destX);
                angle = -Math.toRadians(90) - atan(tg);
            }

        }

        diffX = x - destX;
        diffY = y - destY;
        float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
        velX = ((-1 / distance) * diffX) * 15;
        velY = ((-1 / distance) * diffY) * 15;


    }


    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();

        g2d.rotate(angle, (int) x+175, (int) y+100);
        g2d.drawImage(Game.bat_image, (int) x, (int) y, 350, 200, null);
        g2d.setTransform(old);

    }

    @Override
    public Rectangle getBounds() {
       //  return new Rectangle((int)x,(int)y,350,200);
        return new Rectangle((int)x,(int)y,300,150);
    }
}
