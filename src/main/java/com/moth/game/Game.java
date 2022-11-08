package com.moth.game;

import com.moth.game.enums.ID;
import com.moth.game.graphics.BufferedImageLoader;
import com.moth.game.handlers.Handler;
import com.moth.game.inputs.KeyInput;
import com.moth.game.objects.enemies.Bulb;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 5825867419458798954L;
    public static final int WIDTH = 1280, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;
    private Spawn spawn;

    private Random r;

    public static BufferedImage moth_image;
    public static BufferedImage pudzian_image;
    public static BufferedImage najman_image;
    public static BufferedImage michael_jackson_image;
    public static BufferedImage lamp_image;
    public static BufferedImage bulb_image;

    public Game() {
        handler = new Handler(this);
        hud=new HUD();
        spawn=new Spawn(handler,hud);
        this.addKeyListener(new KeyInput(handler,this));

        new Window(WIDTH, HEIGHT, "Moth in trouble", this);

        handler.addObject(new Bulb(Game.WIDTH/2-35,0, ID.Bulb,handler));
        BufferedImageLoader loader= new BufferedImageLoader();
        //moth_image= ImageIO.read(new FileInputStream("src/main/resources/images/moth.png"));
        moth_image=loader.loadImage("/images/moth.png");
        pudzian_image=loader.loadImage("/images/pudzian.png");
        najman_image=loader.loadImage("/images/najman.png");
        michael_jackson_image=loader.loadImage("/images/mj.png");
        lamp_image=loader.loadImage("/images/lamp.png");
        bulb_image=loader.loadImage("/images/bulb.png");






    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;

    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;     //do sprawdzenia
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
               // System.out.println("FPS: " + frames);
            }
        }

    }

    private void tick() {
        spawn.tick();
        handler.tick();
        hud.tick();


    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g=bs.getDrawGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0,0,WIDTH,HEIGHT);
        drawBackground(g);
        handler.render(g);
        hud.render(g);
        g.dispose();
        bs.show();
    }

    public static float clamp(float var,float min,float max){
        if(var>max)
            return max;
        if(var<min)
            return min;
        return var;
    }


    public static void drawBackground(Graphics g){
        g.setColor(Color.DARK_GRAY);
//        int x3[]={Game.WIDTH/2-133,Game.WIDTH/2,Game.WIDTH/2+133};   //relatywnosc do zarowki jou
//        int y3[]={30,0,30};
//        Polygon p3=new Polygon(x3,y3,3);
//        g.drawPolygon(p3);
//        g.fillPolygon(p3);
//
//        g.drawOval(Game.WIDTH/2-133,20,266,30);
//        g.fillOval(Game.WIDTH/2-133,20,266,30);

       // g.drawImage(Game.lamp_image,Game.WIDTH/2-35,100,70,100,null);

        int x[]={0,0,Game.WIDTH/2};
        int y[]={0,240,0};
        g.setColor(Color.BLACK);
        Polygon p1=new Polygon(x,y,3);   //left corner
        g.drawPolygon(p1);
        g.fillPolygon(p1);

        int x2[]={Game.WIDTH/2,Game.WIDTH,Game.WIDTH};
        int y2[]={0,0,240};
        Polygon p2=new Polygon(x2,y2,3);   //right corner
        g.drawPolygon(p2);
        g.fillPolygon(p2);

        g.drawImage(lamp_image,Game.WIDTH/2-150,-128,300,200,null);
        g.drawImage(bulb_image,Game.WIDTH/2-40,44,80,100,null);


    }

    public static void main(String[] args) {
        new Game();
    }
}
