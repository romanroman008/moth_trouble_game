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

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Game.bulb_image,Game.GAME_WIDTH /2-40,44,80,100,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(Game.GAME_WIDTH /2-40,44,80,100);
    }
}
