package com.moth.game.inputs;

import com.moth.game.Game;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObjectSchema;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] keyDown = new boolean[4];
    private Game game;
    public Graphics graphics;

    public KeyInput(Game game) {
        this.handler = Handler.getInstance();
        this.game = game;
        this.keyDown[0] = false;
        this.keyDown[1] = false;
        this.keyDown[2] = false;
        this.keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(game.gameState== Game.STATE.GAME){

                GameObjectSchema player=handler.getPlayer();
                          //setting player velocity
                    if (key == KeyEvent.VK_LEFT) {         //left
                        player.setVelX(-5);
                        keyDown[0] = true;
                    }
                    if (key == KeyEvent.VK_RIGHT) {        //right
                        player.setVelX(5);
                        keyDown[1] = true;
                    }
                    if (key == KeyEvent.VK_UP) {          //up
                        player.setVelY(-5);
                        keyDown[2] = true;
                    }

                    if (key == KeyEvent.VK_DOWN) {      //down
                        player.setVelY(5);
                        keyDown[3] = true;
                    }



            if(key==KeyEvent.VK_P||key==KeyEvent.VK_ESCAPE){     //game pause
                if(!game.pause)
                game.pause=true;
                else
                    game.pause=false;
            }
        }



    }

    public void keyReleased(KeyEvent e) {

        if(game.gameState==Game.STATE.GAME){
            int key = e.getKeyCode();
            GameObjectSchema player=handler.getPlayer();
            if (key == KeyEvent.VK_LEFT)
                keyDown[0] = false;
            if (key == KeyEvent.VK_RIGHT)
                keyDown[1] = false;
            if (key == KeyEvent.VK_UP)
                keyDown[2] = false;
            if (key == KeyEvent.VK_DOWN)
                keyDown[3] = false;


            //horizontal movement
            if (!keyDown[0] && !keyDown[1])                //preventing player from sliding
                player.setVelX(0);
            //vertical movement
            if (!keyDown[2] && !keyDown[3])
                player.setVelY(0);
        }



    }


}
