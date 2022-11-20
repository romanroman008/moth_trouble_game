package com.moth.game;

import java.awt.*;

public class HUD {
    private int score=0;
    public static Font smallFont;
    public static Font mediumFont;
    public static Font bigFont;
    public static Font hugeFont;
    public static Color color;

    public HUD() {
       smallFont = new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 20);
       mediumFont  = new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 30);
       bigFont  = new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 35);
       hugeFont = new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC,50);
       color=new Color(51,25,0);
    }

    public void tick(){
        score++;

    }

    public void render(Graphics g) {
        g.setFont(smallFont);
        g.setColor(Color.orange);
        g.drawString(""+ score,15,34);
    }

    public int getScore() {
        return score;
    }

    public void resetScore(){
        this.score=0;
    }
}
