package com.moth.game;

import com.moth.game.handlers.Handler;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

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
        Color color=new Color(51,25,0);
        Graphics2D g2d=(Graphics2D)g;

        if (game.gameState == Game.STATE.MENU) {
//            Font font = new Font("arial", 1, 50);
//            Font font2 = new Font("arial", 1, 30);
            drawMenuBackground(g);

            g2d.setStroke(new BasicStroke(4));
            g.setColor(color);
            g.setFont(font);
            g.drawString("Menu", 250, 70);

            g.setFont(font2);
            g.drawString("Play", 290, 160);
            g.drawRect(220, 120, 200, 60);



            g.setColor(color);
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

    private void drawMenuBackground(Graphics g){
        g.drawImage(Game.paper_image,0,0,Game.WIDTH/2,Game.HEIGHT/2,null);

        Graphics2D g2d=(Graphics2D)g;
        AffineTransform old=g2d.getTransform();
        g2d.rotate(Math.toRadians(-45),-22,23);
        g2d.drawImage(Game.bulb_image,-22,23,60,70,null);   //bulb
        g2d.setTransform(old);


        g2d.rotate(Math.toRadians(20),10,Game.HEIGHT/4+100);
        g2d.drawImage(Game.najman_image,12,Game.HEIGHT/4+130,40,70,null);
        g2d.setTransform(old);

        g2d.rotate(Math.toRadians(-30),Game.WIDTH/4+230,Game.HEIGHT/4+150);
        g2d.drawImage(Game.pudzian_image,Game.WIDTH/4+230,Game.HEIGHT/4+155,100,70,null);
        g2d.setTransform(old);

        g2d.rotate(Math.toRadians(-130),Game.WIDTH/4+150,100);
        g2d.drawImage(Game.bat_image,Game.WIDTH/4,100,300,120,null);
        g2d.setTransform(old);

    }


}
