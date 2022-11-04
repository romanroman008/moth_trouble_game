package com.moth.game.objects;

import com.moth.game.Game;
import com.moth.game.handlers.Handler;
import com.moth.game.enums.ID;

import java.awt.*;

public class Player extends GameObject {

    Handler handler;
    GameObject bulb;
    // HashMap<BonusType,Integer> bonuses;
    int counter;
    float bonusSpeedMultiplier;
    boolean gotcha=false;

    public Player(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX = 0;
        velY = 0;
        counter=0;
       // bonuses=new HashMap<>();
        this.handler = handler;
        this.bulb = handler.getObjects().stream()
                .filter(b -> b.getId().equals(ID.Bulb))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void tick() {
        if(!gotcha||bonusSpeedMultiplier!=0){
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


        x=Game.clamp(x,0,Game.WIDTH-44);
        y=Game.clamp(y,0,Game.HEIGHT-70);
        if(y<240){
            if(x<(float)Game.WIDTH/2){
                y=Game.clamp(y,-3f/8f*x+240,Game.WIDTH);
            }
            if(x>(float)Game.WIDTH/2){
                y=Game.clamp(y,3f/8f*x-240,Game.WIDTH);
                //x=Game.clamp(x,0,8f/3f*(y+240f));
            }

        }


//        if(!bonuses.isEmpty()){
//           bonusCountdown();
//        }




        float diffX = x - bulb.getX() -25;
        float diffY = y - bulb.getY() - 25;
        float distance = (float) Math.sqrt(Math.pow(diffX + 25, 2) + Math.pow(diffY + 25, 2));

        x+= ((-1/distance)*diffX*2);
        y+= ((-1/distance)*diffY*2);
        velX=Game.clamp(velX,-10,10);
        velY=Game.clamp(velY,-10,10);


    }

    private void gotchaCountdown(){
        counter++;
        if(counter>=100){
            counter=0;
            gotcha=false;
//            bonuses.forEach((k,v)->v--);
//            bonuses.entrySet().removeIf(b->b.getValue()==0);
        }
    }



    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int) x, (int) y, 32, 32);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

//    public void addBonus(BonusType bonus){
//        if(!bonuses.containsKey(bonus)){
//            this.bonuses.put(bonus,5);
//        }
//        else{
//            this.bonuses.remove(bonus);
//            this.bonuses.put(bonus,5);
//        }
//
//    }


    public float getBonusSpeedMultiplier() {
        return bonusSpeedMultiplier;
    }

    public void setBonusSpeedMultiplier(float bonusSpeedMultiplier) {
        this.bonusSpeedMultiplier = bonusSpeedMultiplier;
    }

    public void setGotcha() {
        this.gotcha = true;
    }
}
