package com.moth.game.handlers;

import com.moth.game.Game;
import com.moth.game.objects.GameObject;
import com.moth.game.enums.ID;
import com.moth.game.objects.Player;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> objects = new LinkedList<GameObject>();
    BonusHandler bonusHandler;

    public Handler(Game game) {
        this.bonusHandler = new BonusHandler(game,this);
    }

    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);

            tempObject.tick();
        }
       // objects.forEach(GameObject::tick);
        bonusHandler.tick();
    }

    public void render(Graphics g) {
        objects.forEach(o -> o.render(g));
        bonusHandler.render(g);
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



    public void removeBonusesAndEnemies(){
        objects.removeIf(o->o.getId()==ID.Bonus||o.getId()==ID.Enemy);
    }

    public LinkedList<GameObject> getObjects() {
        return objects;
    }

    public BonusHandler getBonusHandler() {
        return bonusHandler;
    }
}
