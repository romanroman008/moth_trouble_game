package com.moth.game;

import com.moth.game.handlers.Handler;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class Menu extends MouseAdapter  {

    private static Menu INSTANCE;

    Game game;
    Handler handler;
    HUD hud;

    private Menu(){}

    public static Menu getInstance(){
        if(INSTANCE==null)
            INSTANCE=new Menu();
        return INSTANCE;
    }



    public Menu(Game game){
        this.game = game;
        this.hud=HUD.getInstance();
        this.handler=Handler.getInstance();
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.MENU) {
            //play
            if (mouseOver(mx, my, 220, 120, 200, 60)) {
                game.gameState = Game.STATE.GAME;
                game.window.changeSize(Game.GAME_WIDTH,Game.GAME_HEIGHT);

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

                if(mouseOver(mx,my,190, 320, 260, 60)) {
                    game.gameState= Game.STATE.MENU;
                    game.reset();
            }

        }
    }


    public void render(Graphics g){

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


            g.drawString("Wyj??cie", 263, 360);
            g.drawRect(220, 320, 200, 60);
        } else if (game.gameState == Game.STATE.HELP) {

            drawMenuBackground(g);
            g2d.setStroke(new BasicStroke(4));

            g.setFont(HUD.mediumFont);
              g.drawString("Dasz rad?? wariacie.", 180, 230);
             g.drawRect(100, 120, 400, 200);


            g.setFont(HUD.mediumFont);
            g.drawString("Wr????", 265, 390);
            g.drawRect(225, 355, 150, 50);
        }
        else if(game.gameState== Game.STATE.END){
            drawEndBackground(g);
            g2d.setStroke(new BasicStroke(4));
            g.setColor(HUD.color);

            g.setFont(HUD.hugeFont);
            g.drawString("Sp??lila t?? lampa", 130, 150);
            g.drawString("nebo t?? se??ral netop??r", 50, 200);
            g.drawString("Tw??j wynik: " + hud.getScore(), 120, 270);

            g.setFont(HUD.mediumFont);
            g.drawString("Spr??buj ponownie", 210, 360);
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
        g.drawImage(Game.paper_image,0,0,Game.MENU_WIDTH,Game.MENU_HEIGHT,null);

        Graphics2D g2d=(Graphics2D)g;
        AffineTransform old=g2d.getTransform();
        g2d.rotate(Math.toRadians(-45),-22,23);
        g2d.drawImage(Game.bulb_image,-22,23,60,70,null);   //bulb
        g2d.setTransform(old);


    }




    private void drawMenuBackground(Graphics g){
        g.drawImage(Game.paper_image,0,0,Game.MENU_WIDTH,Game.MENU_HEIGHT,null);

        Graphics2D g2d=(Graphics2D)g;
        AffineTransform old=g2d.getTransform();
        g2d.rotate(Math.toRadians(-45),-22,23);
        g2d.drawImage(Game.bulb_image,-22,23,60,70,null);   //bulb
        g2d.setTransform(old);


        g2d.rotate(Math.toRadians(20),10,Game.MENU_HEIGHT /2+100);
        g2d.drawImage(Game.najman_image,12,Game.MENU_HEIGHT /2+130,40,70,null);
        g2d.setTransform(old);

        g2d.rotate(Math.toRadians(-30),Game.MENU_WIDTH /2+230,Game.MENU_HEIGHT /2+150);
        g2d.drawImage(Game.pudzian_image,Game.MENU_WIDTH /2+230,Game.MENU_HEIGHT /2+155,100,70,null);
        g2d.setTransform(old);

        g2d.rotate(Math.toRadians(-130),Game.MENU_WIDTH /2+150,100);
        g2d.drawImage(Game.bat_image,Game.MENU_WIDTH/2,100,300,120,null);
        g2d.setTransform(old);

    }


}
