package com.moth.game.objects;

import com.moth.game.Game;
import com.moth.game.handlers.Handler;
import com.moth.game.enums.ID;

import java.awt.*;

public class Player extends GameObject {

    Handler handler;
    GameObject bulb;
    int counter;
    float bonusSpeedMultiplier;
    boolean gotcha=false;
    boolean turnOffKeys =false;
    private float multiGamePlayGuy;



    public Player(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX = 0;
        velY = 0;
        counter=0;
        multiGamePlayGuy=1;
        this.handler = handler;
        this.bulb = handler.getObjects().stream()
                .filter(b -> b.getId().equals(ID.Bulb))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void tick() {

        if(collision()){
            setInstantLose();
        }

        if((!gotcha)&&!turnOffKeys){
            if(bonusSpeedMultiplier!=0){
                x += velX * bonusSpeedMultiplier;
                y += velY * bonusSpeedMultiplier;
            }
            else{
                x += velX;
                y += velY;
            }
        }
        else{
            gotchaCountdown();
        }


        x=Game.clamp(x,-5,Game.WIDTH-75);
        y=Game.clamp(y,40,Game.HEIGHT-85);
        if(y<240){
            if(x<(float)Game.WIDTH/2){
                y=Game.clamp(y,-3f/8f*(x+12)+240,Game.WIDTH);
            }
            if(x>(float)Game.WIDTH/2){
                y=Game.clamp(y,3f/8f*(x+55)-240,Game.WIDTH);
                //x=Game.clamp(x,0,8f/3f*(y+240f));
            }

        }







        float diffX = x - bulb.getX();
        float diffY = y - bulb.getY() - 60;
        float distance = (float) Math.sqrt(Math.pow(diffX + 25, 2) + Math.pow(diffY + 25, 2));

        x+= ((-1/distance)*diffX*2)*multiGamePlayGuy;
        y+= ((-1/distance)*diffY*2)*multiGamePlayGuy;
        velX=Game.clamp(velX,-10,10);
        velY=Game.clamp(velY,-10,10);
       // multiGamePlayGuy=multiGamePlayGuy+0.001f;

    }

    private void gotchaCountdown(){
        counter++;
        if(counter>=100){
            counter=0;
            gotcha=false;
        }
    }



    @Override
    public void render(Graphics g) {
        g.drawImage(Game.moth_image,(int)x,(int)y,70,50,null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 65, 52);
    }


    public float getBonusSpeedMultiplier() {
        return bonusSpeedMultiplier;
    }

    public void setBonusSpeedMultiplier(float bonusSpeedMultiplier) {
        this.bonusSpeedMultiplier = bonusSpeedMultiplier;
    }

    public void setGotcha() {
       // Game.end=true;
        if(!this.gotcha){
           // Game.end=true;
          //  System.out.println("Gotya");
            this.gotcha = true;
        }

    }

    private boolean collision() {
        for (GameObject object : handler.getObjects()) {
            if (object.getId() == ID.Bat||object.getId()== ID.Bulb) {
                if (getBounds().intersects(object.getBounds())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void turnOffKeys(){
        this.turnOffKeys =true;
    }

    private void setInstantLose(){
        Game.end=true;
        System.out.println("tu");
    }
}
