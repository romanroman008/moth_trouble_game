package com.moth.game.objects.enemies;

import com.moth.game.Game;
import com.moth.game.enums.ID;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Bat extends GameObject {
    Random r;
    int corner;
    float destX;
    float destY;
    float a;
    float b;
    private float diffX;
    private float diffY;

    public Bat(float x, float y, ID id) {
        super(x, y, id);
        r=new Random();
        corner=0;
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
        if(x<0||x>1280||y<0||y>960) {
            coordinatesRandomizer();
//            a=(destX-x)/(destY-y);
//            b=y-a*x;
//            velY=a*x+b;
            if(corner<4)
                corner++;
            else
                corner=0;


        }

        diffX = x-destX;
        diffY = y-destY;
        float distance = (float)Math.sqrt(Math.pow(diffX,2)+Math.pow(diffY,2));
        x+=((-1/distance)* diffX)*10;
        y+=((-1/distance)* diffY)*10;


//            x+=velX;
//            y+=velY;

    }

    private void coordinatesRandomizer() {
        switch (corner) {
            case 0 -> {
                x = 1;
                y =1;
                destX = 1300;
                destY = r.nextInt(200,960);
                //velX=r.nextInt(15)+5;
            }
            case 1 -> {
                x = 1280;
                y = 960;
                destX = -20;
                destY = r.nextInt(200,950);
               // velX=-(r.nextInt(15)+5);
            }
            case 2 -> {
                x = 1;
                y = 960;
                destX = 1300;
                destY = r.nextInt(200,960);
               // velX=-(r.nextInt(15)+5);
            }
            case 3 -> {
                x = 1280;
                y = -20;
                destX = r.nextInt(300,1280);
                destY = 1000;
               // velX=(r.nextInt(15)+5);
            }
        }
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.white);
//        g.fillRect((int)x,(int)y,20,20);
        Graphics2D g2d=(Graphics2D) g;
     //   AffineTransform old=g2d.getTransform();
        //g2d.rotate(Math.tan(diffY/diffX));
      //  g2d.rotate(Math.toRadians(270));
        g2d.drawImage(Game.moth_image,(int)x,(int)y,70,50,null);
      //  g2d.setTransform(old);

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
