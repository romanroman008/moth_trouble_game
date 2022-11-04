package com.moth.game.objects.enemies;

import com.moth.game.Game;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;
import com.moth.game.enums.ID;
import com.moth.game.objects.Trail;

import java.awt.*;
import java.util.Random;

public class BulbMissile extends GameObject {

    Handler handler;
    private Random r;


    public BulbMissile(float x, float y, ID id, float velY, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        r = new Random();
       // this.velY = velY;
//        this.velX = r.nextInt(10 + 10) - 10;
//        this.velY = r.nextInt(20);
        missileRandomizer();
        if (y <= 0 || y >= Game.HEIGHT - 50)
            this.handler.removeObject(this);

        if (x <= 0 || x >= Game.WIDTH)
            this.handler.removeObject(this);

    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 50)
            this.handler.removeObject(this);

        if (x <= 0 || x >= Game.WIDTH)
            this.handler.removeObject(this);

        if(collision())
            handler.getPlayer().setGotcha();

        handler.addObject(new Trail(x, y, 16, 16, Color.yellow, 0.05f, ID.Enemy, handler));
    }

    private boolean collision(){
        for (GameObject object : handler.getObjects()) {
            if(object.getId()== ID.Player){
                if(getBounds().intersects(object.getBounds())) {
                    System.out.println("gotcha");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
       // g.fillRect((int)x,(int)y,16,16);
        g.fillOval((int)x,(int)y,16,16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    private void missileRandomizer(){

        float diffX = (r.nextFloat(1280)-x);
        float diffY = -((r.nextFloat(960-220)+220)-y);
        float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
        this.velX = ((-1 / distance) * diffX) * 10;
        this.velY = ((-1 / distance) * diffY) * 10;
    }
}
