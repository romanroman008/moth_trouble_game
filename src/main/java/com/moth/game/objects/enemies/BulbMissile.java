package com.moth.game.objects.enemies;

import com.moth.game.Game;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;
import com.moth.game.enums.ID;
import com.moth.game.objects.Trail;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class BulbMissile extends GameObject {

    Handler handler;
    private Random r;
    Color color;


    public BulbMissile(float x, float y, ID id, float velY, Handler handler) {
        super(x, y, id);

        color=new Color(255,255,153,70);
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

       // handler.addObject(new Trail(x, y, 16, 16, color, 0.05f, ID.Enemy, handler));
    }

    private boolean collision(){
        for (GameObject object : handler.getObjects()) {
            if(object.getId()== ID.Player){
                if(getBounds().intersects(object.getBounds())) {
                   // System.out.println("gotcha");
                    return true;
                }
            }
        }
        return false;


    }

    @Override
    public void render(Graphics g) {
       // Graphics2D g2d=(Graphics2D) g;
        //g2d.setComposite(makeTransparent(0.5F));
       // g2d.setComposite(makeTransparent(0.5f));
        g.setColor(new Color(255,255,153,100));
       // g.fillRect((int)x,(int)y,16,16);\

        g.fillOval((int)x,(int)y,16,16);
      // g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
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
