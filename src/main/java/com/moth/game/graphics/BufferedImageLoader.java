package com.moth.game.graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BufferedImageLoader {

    BufferedImage image;

    public BufferedImage loadImage(String path){
        try {
       //     InputStream in=getClass().getResourceAsStream(path);
            image = ImageIO.read(getClass().getResource(path));
           // image = new ImageIcon("moth.png").getImage()
       // return ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    //  return null;
        return image;
    }
}
