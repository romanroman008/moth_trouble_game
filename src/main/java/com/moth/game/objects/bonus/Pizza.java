package com.moth.game.objects.bonus;

import com.moth.game.Game;
import com.moth.game.enums.BonusType;
import com.moth.game.enums.ID;
import com.moth.game.handlers.Handler;

import java.awt.*;

class Pizza extends Bonus {

    Pizza(double x, double y, double height, double width, double velX, double velY, BonusType bonusType) {
        super(x, y, height, width, velX, velY, bonusType);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Game.pizza_image, (int) x, (int) y, (int) width, (int) height, null);
    }

    @Override
    public void bonusPower() {
        getHandler().getPlayer().setBonusSpeedMultiplier(0.7f);
    }

    @Override
    public void bonusDepower() {
        getHandler().getPlayer().setBonusSpeedMultiplier(1);
    }
}
