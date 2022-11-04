package com.moth.game.objects;

import com.moth.game.handlers.Handler;
import com.moth.game.enums.ID;

import java.awt.*;

public abstract class GameObject {
    protected float x, y, height, width, velX, velY;
    private ID id;
    private Handler handler;


    public GameObject(float x, float y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public GameObject(float x, float y, float height, float width, float velX, float velY, ID id) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.velX = velX;
        this.velY = velY;
        this.id = id;
    }


    public GameObject(float x, float y, float height, float width, ID id) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.id = id;
    }

    public GameObject(float x, float y, ID id, Handler handler) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler=handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public ID getId() {
        return id;
    }

    public void changeVelX(float velX){
        this.velX+=velX;
    }
    public void changeVelY(float velY){
        this.velY+=velY;
    }

    public Handler getHandler() {
        return handler;
    }
}
