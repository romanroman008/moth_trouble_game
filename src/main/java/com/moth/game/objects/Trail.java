package com.moth.game.objects;

import com.moth.game.handlers.Handler;
import com.moth.game.enums.ID;

import java.awt.*;

public class Trail extends GameObject {

    private float alpha = 1;
    private float life;
    private Handler handler;
    private Color color;

    public Trail(float x, float y, float height, float width, Color color, float life, ID id, Handler handler) {
        super(x, y, height, width, id);
        this.color = color;
        this.height = height;
        this.width = width;
        this.life = life;
        this.handler = handler;

    }

    @Override
    public void tick() {
        if (alpha > life) {
            alpha -= (life - 0.01f);
        } else handler.removeObject(this);
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
       // g.fillRect((int)x,(int)y,(int)width,(int)height);
        g.fillOval((int)x,(int)y,(int)width,(int)height);
        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,(int)width,(int)height);
    }
}
