package com.moth.game.objects.enemies;

import com.moth.game.Game;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;
import com.moth.game.enums.ID;

import java.awt.*;
import java.util.Random;

public class Bulb extends GameObject {
    Handler handler;
    Random r;

    public Bulb(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        r=new Random();
    }

    @Override
    public void tick() {
//        if(r.nextInt(10)==0){
//            handler.addObject(new BulbMissile(x+35,y,ID.Enemy,5,handler));
//        }
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.yellow);
//        g.fillOval((int) x, (int) y, 70, 100);
       //g.drawImage(Game.lamp_image,(int)x,(int)y,70,100,null);
        //g.drawImage(Game.lamp_image,(int)x,(int)y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 50, 50);
    }
}
