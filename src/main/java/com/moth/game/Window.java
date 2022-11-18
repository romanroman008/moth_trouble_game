package com.moth.game;

import com.moth.game.Game;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    private static final long serialVersionUID = -8553933984764625043L;
    private JFrame frame;

    public Window(int width, int height, String title, Game game){
        frame=new JFrame(title);
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width*2, height*2));
        frame.setMinimumSize(new Dimension(width,height));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }


    public void changeSize(int width,int height){
        frame.setPreferredSize(new Dimension(width,height));
        //frame.setMaximumSize(new Dimension(width,height));
      //  frame.setMinimumSize(new Dimension(width,height));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

}
