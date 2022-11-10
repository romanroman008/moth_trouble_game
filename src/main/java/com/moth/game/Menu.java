package com.moth.game;

import com.moth.game.handlers.Handler;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter  {

    Game game;
    Handler handler;
    HUD hud;



    public Menu(Game game,Handler handler,HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud=hud;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.MENU) {
            //play
            if (mouseOver(mx, my, 220, 120, 200, 60)) {
                game.gameState = Game.STATE.GAME;
                game.window.changeSize(Game.WIDTH,Game.HEIGHT);

            }

            if (mouseOver(mx, my, 220, 220, 200, 60)) {
                game.gameState = Game.STATE.HELP;
            }


            if (mouseOver(mx, my, 220, 320, 200, 60)) {
                System.exit(1);
            }
        }
        else if (game.gameState == Game.STATE.HELP) {
            if (mouseOver(mx, my, 220, 320, 200, 60)) {
                game.gameState = Game.STATE.MENU;
            }
        }
    }

//    public void mouseReleased(MouseEvent e) {
//        int mx = e.getX();
//        int my = e.getY();
//
//        if (game.gameState == Game.STATE.MENU) {
//            //play
//            if (mouseOver(mx, my, 220, 120, 200, 60)) {
//                game.gameState = Game.STATE.GAME;
//                game.window.changeSize(Game.WIDTH,Game.HEIGHT);
//            }
//        }
//    }

//    public void mouseMoved(MouseEvent e){
//        int mx = e.getX();
//        int my = e.getY();
//        System.out.println(mx);
//        System.out.println(my);
//
//        if (game.gameState == Game.STATE.MENU) {
//            //play
//            if (mouseOver(mx, my, 220, 120, 200, 60)) {
//                game.gameState = Game.STATE.GAME;
//                game.window.changeSize(Game.WIDTH,Game.HEIGHT);
//            }
//        }
//    }

    public void render(Graphics g){
        Font font = new Font("arial", 1, 50);
        Font font2 = new Font("arial", 1, 30);
        Font font3 = new Font("arial", 1, 20);

        if (game.gameState == Game.STATE.MENU) {
//            Font font = new Font("arial", 1, 50);
//            Font font2 = new Font("arial", 1, 30);

            g.setColor(Color.white);
            g.setFont(font);
            g.drawString("Menu", 250, 70);

            g.setFont(font2);
            g.drawString("Play", 290, 160);
            g.drawRect(220, 120, 200, 60);



            g.setColor(Color.white);
            g.drawString("Help", 290, 260);
            g.drawRect(220, 220, 200, 60);


            g.drawString("Quit", 290, 360);
            g.drawRect(220, 320, 200, 60);
        } else if (game.gameState == Game.STATE.HELP) {

            g.setFont(font3);
            g.setColor(Color.white);
            g.drawString("Use arrows to move and dodge enemies. Peace man", 80, 260);
            // g.drawRect(220, 120, 200, 200);

            g.setFont(font2);
            g.setColor(Color.white);
            g.drawString("Back", 290, 360);
            g.drawRect(220, 320, 200, 60);
        }

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width)
            if (my > y && my < y + height){
                return true;
            }

        return false;

    }
}
