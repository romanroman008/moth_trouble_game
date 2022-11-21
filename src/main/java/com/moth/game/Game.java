package com.moth.game;

import com.moth.game.enums.ID;
import com.moth.game.graphics.BufferedImageLoader;
import com.moth.game.handlers.Handler;
import com.moth.game.handlers.Spawn;
import com.moth.game.inputs.KeyInput;
import com.moth.game.objects.MenuMoth;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 5825867419458798954L;
    public static final int WIDTH = 1280, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    public Window window;

    private Handler handler;
    private HUD hud;
    private Spawn spawn;
    private Menu menu;


    public static boolean end;
    public boolean pause;


    public enum STATE {
        MENU,
        GAME,
        HELP,
        END
    }

    public STATE gameState = STATE.MENU;

    public static BufferedImage moth_image;
    public static BufferedImage pudzian_image;
    public static BufferedImage najman_image;
    public static BufferedImage michael_jackson_image;
    public static BufferedImage lamp_image;
    public static BufferedImage bulb_image;
    public static BufferedImage bat_image;
    public static BufferedImage pizza_image;
    public static BufferedImage paper_image;
    public static BufferedImage background_image;
    public static BufferedImage illuminati_image;
    public static BufferedImage jaszczur_image;

    public Game() {
        handler = new Handler(this);
        hud = new HUD();
        spawn = new Spawn(handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        menu = new Menu(this, handler, hud);
        this.addMouseListener(menu);
        pause = false;

        window = new Window(WIDTH / 2, HEIGHT / 2, "Moth in trouble", this);
       // window.changeSize(WIDTH, HEIGHT);


        BufferedImageLoader loader = new BufferedImageLoader();
        moth_image = loader.loadImage("/images/moth.png");
        pudzian_image = loader.loadImage("/images/pudzian.png");
        najman_image = loader.loadImage("/images/najman.png");
        michael_jackson_image = loader.loadImage("/images/mj.png");
        lamp_image = loader.loadImage("/images/lamp.png");
        bulb_image = loader.loadImage("/images/bulb.png");
        bat_image = loader.loadImage("/images/bat.png");
        pizza_image = loader.loadImage("/images/pizza.png");
        paper_image = loader.loadImage("/images/paper.png");
        background_image = loader.loadImage("/images/background.png");
        illuminati_image = loader.loadImage("/images/illuminati.png");
        jaszczur_image = loader.loadImage("/images/jaszczur.png");




        handler.addObject(new MenuMoth(100, 100, ID.MenuParticle, handler));


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
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
            }
        }

    }

    private void tick() {

        if (gameState == STATE.GAME) {
            if (!pause) {
                handler.clearMenuParticles();
                spawn.tick();
                handler.tick();
                hud.tick();
                if (end) {
                    finish();
                }
            }


        } else if (gameState == STATE.MENU || gameState == STATE.END || gameState == STATE.HELP) {
            handler.tick();
        }


    }


    public void reset() {
        spawn.resetScore();
        handler.removeAll();
        handler.addObject(new MenuMoth(100, 100, ID.MenuParticle, handler));
    }

    public void finish() {
        handler.getBonusHandler().removeBonuses();
        window.changeSize(Game.WIDTH / 2, Game.HEIGHT / 2);
        gameState = STATE.END;
        end = false;
        handler.removeAll();
        MenuMoth menuParticle = new MenuMoth(100, 100, ID.MenuParticle, handler);
        menuParticle.thisIsTheEnd = true;
        handler.addObject(menuParticle);
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        if (gameState == STATE.GAME) {


            g.drawImage(background_image, 0, 0, Game.WIDTH, Game.HEIGHT, null);
            drawBackground(g);
            handler.render(g);
            hud.render(g);

            if (pause) {
                g.setColor(Color.white);
                g.setFont(HUD.mediumFont);
                g.drawString("Pauza", WIDTH / 2 - 30, HEIGHT / 2);
            }

        } else if (gameState == STATE.MENU || gameState == STATE.HELP || gameState == STATE.END) {
            menu.render(g);
            handler.render(g);
        }

        g.dispose();
        bs.show();
    }


    public static float clamp(float var, float min, float max) {        //preventing values from going beyond the limits (sky IS the limit XD)
        if (var > max)
            return max;
        if (var < min)
            return min;
        return var;
    }


    public static void drawBackground(Graphics g) {
        g.setColor(Color.DARK_GRAY);


        int x[] = {0, 0, Game.WIDTH / 2};
        int y[] = {0, 240, 0};
        g.setColor(new Color(0, 0, 0, 127));
        Polygon p1 = new Polygon(x, y, 3);   //left corner
        g.drawPolygon(p1);
        g.fillPolygon(p1);

        int x2[] = {Game.WIDTH / 2, Game.WIDTH, Game.WIDTH};
        int y2[] = {0, 0, 240};
        Polygon p2 = new Polygon(x2, y2, 3);   //right corner
        g.drawPolygon(p2);
        g.fillPolygon(p2);


        g.drawImage(lamp_image, Game.WIDTH / 2 - 150, -128, 300, 200, null);
        g.drawImage(bulb_image, Game.WIDTH / 2 - 40, 44, 80, 100, null);


    }

    public static void main(String[] args) {
        new Game();
    }
}










































































































