package com.moth.game.objects.bonus;

import com.moth.game.Game;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;
import com.moth.game.enums.ID;

import java.awt.*;

public abstract class PrimaryBonus extends GameObject implements Bonus {
    Handler handler;
    float life=1;
    int i=0;
    float temp;

    public PrimaryBonus(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler=handler;
        velX=4;
        velY=5;
        temp=velY;
    }

    public PrimaryBonus(float x, float y, float height, float width, float velX, float velY, ID id) {
        super(x, y, height, width, velX, velY, id);
    }

    public PrimaryBonus(float x, float y, float height, float width, ID id) {
        super(x, y, height, width, id);
    }

    @Override
    public void tick() {
//       i++;
//       if(i>=20){
//           velY=0;
//           if(i>=30){
//               velY=-temp;
//               temp=-temp;
//               i=0;
//           }
//       }



        velY=(float) Math.sin(x/70)*3;

       // if(life>0){
            x+=velX;
            y+=velY;
     //       life-=0.005f;
     //   }
     //   else{
    //       handler.removeObject(this);
    //    }

        if(x<=0||x>Game.WIDTH-32)
        velX=-velX;
       

        //x= Game.clamp(x,0,Game.WIDTH);

       // handler.addObject(new Trail(x, y, 32, 32, Color.green, 0.05f, ID.Bonus, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int)x,(int)y,32,32);
    }

    private boolean collision(){
        for (GameObject object : handler.getObjects()) {
            if(object.getId()==ID.Player){
                if(getBounds().intersects(object.getBounds())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }



}
