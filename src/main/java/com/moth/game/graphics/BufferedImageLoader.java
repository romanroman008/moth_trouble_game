package com.moth.game.graphics;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


public class BufferedImageLoader {

    BufferedImage image;

    public BufferedImage loadImage(String path) {
        try {
        InputStream resourceBuff = getClass().getResourceAsStream(path);
        image = ImageIO.read(resourceBuff);
        return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
