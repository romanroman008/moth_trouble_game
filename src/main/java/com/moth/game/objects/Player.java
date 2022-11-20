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



    public Player(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX = 0;
        velY = 0;
        counter=0;
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
                x += velX * bonusSpeedMultiplier;                     //making player move if he has any bonuses
                y += velY * bonusSpeedMultiplier;
            }
            else{
                x += velX;                                            //making player move without bonuses
                y += velY;
            }
        }
        else{
            gotchaCountdown();
        }

                                                       //setting player's bounds of the screen:
        x=Game.clamp(x,-5,Game.WIDTH-75);    //horizontal
        y=Game.clamp(y,40,Game.HEIGHT-85);   //vertical
        if(y<240){
            if(x<(float)Game.WIDTH/2){
                y=Game.clamp(y,-3f/8f*(x+12)+240,Game.WIDTH);          //left upper corner
            }
            if(x>(float)Game.WIDTH/2){
                y=Game.clamp(y,3f/8f*(x+55)-240,Game.WIDTH);           //right upper corner
            }

        }







        float diffX = x - bulb.getX();
        float diffY = y - bulb.getY() - 60;
        float distance = (float) Math.sqrt(Math.pow(diffX + 25, 2) + Math.pow(diffY + 25, 2)); //calculating distance from player to bulb

        x+= ((-1/distance)*diffX*2);     //setting horizontal velocity toward bulb
        y+= ((-1/distance)*diffY*2);     //setting vertical velocity toward bulb
      //  velX=Game.clamp(velX,-10,10);
       // velY=Game.clamp(velY,-10,10);

    }

    private void gotchaCountdown(){    //setting countdown when player is hit by missile
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
        if(!this.gotcha){
            this.gotcha = true;
        }

    }

    private boolean collision() {                                 //checking if bat hit player
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
    }
}
