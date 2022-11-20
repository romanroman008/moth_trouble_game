package com.moth.game.inputs;

import com.moth.game.Game;
import com.moth.game.handlers.Handler;
import com.moth.game.objects.GameObject;
import com.moth.game.enums.ID;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] keyDown = new boolean[4];
    private Game game;
    public Graphics graphics;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
        this.keyDown[0] = false;
        this.keyDown[1] = false;
        this.keyDown[2] = false;
        this.keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(game.gameState== Game.STATE.GAME){
            for (GameObject object : this.handler.getObjects()) {
                if (object.getId() == ID.Player) {            //setting player velocity
                    if (key == KeyEvent.VK_LEFT) {         //left
                        object.setVelX(-5);
                        keyDown[0] = true;
                    }
                    if (key == KeyEvent.VK_RIGHT) {        //right
                        object.setVelX(5);
                        keyDown[1] = true;
                    }
                    if (key == KeyEvent.VK_UP) {          //up
                        object.setVelY(-5);
                        keyDown[2] = true;
                    }

                    if (key == KeyEvent.VK_DOWN) {      //down
                        object.setVelY(5);
                        keyDown[3] = true;
                    }

                }
            }
            if(key==KeyEvent.VK_P){     //game pause
                if(!game.pause)
                game.pause=true;
                else
                    game.pause=false;
            }
        }



    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (GameObject object : this.handler.getObjects()) {
            if (object.getId() == ID.Player) {
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
                    object.setVelX(0);
                //vertical movement
                if (!keyDown[2] && !keyDown[3])
                    object.setVelY(0);
            }
        }
    }


}
