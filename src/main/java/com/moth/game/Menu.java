package com.moth.game;

import com.moth.game.enums.ID;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.MenuParticle;

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
            if (mouseOver(mx, my, 225, 355, 150, 50)) {
                game.gameState = Game.STATE.MENU;
            }
        }
        else if(game.gameState== Game.STATE.END){
            if(mouseOver(mx,my,190, 320, 260, 60))

                game.gameState= Game.STATE.MENU;
            game.reset();
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
       // Color color=new Color(51,25,0);
        Graphics2D g2d=(Graphics2D)g;

        if (game.gameState == Game.STATE.MENU) {
            drawMenuBackground(g);

            g2d.setStroke(new BasicStroke(4));
            g.setColor(HUD.color);
            g.setFont(HUD.hugeFont);
            g.drawString("Menu", 255, 80);

            g.setFont(HUD.bigFont);
            g.drawString("Zagraj", 270, 160);
            g.drawRect(220, 120, 200, 60);



            g.setColor(HUD.color);
            g.drawString("Pomoc", 270, 260);
            g.drawRect(220, 220, 200, 60);


            g.drawString("Wyjście", 263, 360);
            g.drawRect(220, 320, 200, 60);
        } else if (game.gameState == Game.STATE.HELP) {

            drawMenuBackground(g);
            g2d.setStroke(new BasicStroke(4));

            g.setFont(HUD.mediumFont);
              g.drawString("Dasz radę wariacie.", 180, 230);
             g.drawRect(100, 120, 400, 200);


            g.setFont(HUD.mediumFont);
            g.drawString("Wróć", 265, 390);
            g.drawRect(225, 355, 150, 50);
        }
        else if(game.gameState== Game.STATE.END){
            drawEndBackground(g);
            g2d.setStroke(new BasicStroke(4));
            g.setColor(HUD.color);

            g.setFont(HUD.hugeFont);
            g.drawString("Spálila tě lampa", 130, 150);
            g.drawString("nebo tě sežral netopýr", 50, 200);
            g.drawString("Twój wynik: " + hud.getScore(), 120, 270);

            g.setFont(HUD.mediumFont);
            g.drawString("Spróbuj ponownie", 210, 360);
            g.drawRect(190, 320, 260, 60);
        }

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width)
            if (my > y && my < y + height){
                return true;
            }

        return false;

    }
    private void drawEndBackground(Graphics g){
        g.drawImage(Game.paper_image,0,0,Game.WIDTH/2,Game.HEIGHT/2,null);

        Graphics2D g2d=(Graphics2D)g;
        AffineTransform old=g2d.getTransform();
        g2d.rotate(Math.toRadians(-45),-22,23);
        g2d.drawImage(Game.bulb_image,-22,23,60,70,null);   //bulb
        g2d.setTransform(old);


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
