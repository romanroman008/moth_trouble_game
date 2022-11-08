package com.moth.game;

import java.awt.*;

public class HUD {
    private int score=0;


    public void tick(){
        score++;

    }

    public void render(Graphics g) {
        Font font = new Font("arial", 1, 20);
        g.setFont(font);
        g.setColor(Color.orange);
        g.drawString("Score: "+ score,15,64);
    }

    public int getScore() {
        return score;
    }
}
