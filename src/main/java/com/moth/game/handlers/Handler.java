package com.moth.game.handlers;

import com.moth.game.objects.GameObject;
import com.moth.game.enums.ID;
import com.moth.game.objects.Player;

import java.awt.*;
import java.util.LinkedList;

public final class Handler {

    private static Handler INSTANCE;

    private Handler(){}

    public static Handler getInstance(){
        if(INSTANCE==null)
            INSTANCE=new Handler();
        return INSTANCE;
    }

    LinkedList<GameObject> objects = new LinkedList<GameObject>();






    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);

            tempObject.tick();                   //invoking tick method in every object
        }
                     //invoking tick method in every collected bonus
    }

    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);

            tempObject.render(g);     //invoking render method in every object
        }

    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void removeObject(GameObject object) {
        objects.remove(object);
    }

    public void removeAll() {
        objects.removeAll(objects);  //idk
    }

    public Player getPlayer(){
        return  (Player)objects.stream()
                .filter(o->o.getId()==ID.Player)
                .findAny().get();
    }

    public void removePlayer() {
        objects.removeIf(o -> o.getId().equals(ID.Player));
    }

    public void removeBat(){
        objects.removeIf(o -> o.getId().equals(ID.Bat));
    }

    public void clearMenuParticles(){
        objects.removeIf(o->o.getId().equals(ID.MenuParticle));
    }



    public void removeBonusesAndEnemies(){
        objects.removeIf(o->o.getId()==ID.Bonus||o.getId()==ID.Enemy);
    }

    public LinkedList<GameObject> getObjects() {
        return objects;
    }


}
