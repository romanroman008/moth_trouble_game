package com.moth.game.handlers;

import com.moth.game.Game;
import com.moth.game.HUD;
import com.moth.game.objects.GameObjectSchema;
import com.moth.game.objects.bonus.Bonus;

import java.awt.*;
import java.util.HashMap;

public final class BonusHandler {

    private static BonusHandler INSTANCE;





    int counter = 0;
    // int trueCounter;
    Handler handler;
    GameObjectSchema player;
    HashMap<Bonus, Integer> bonuses = new HashMap<>();
    float transparency;

    private BonusHandler(){
        this.handler=handler.getInstance();
    }
    public static BonusHandler getInstance(){
        if(INSTANCE==null)
            INSTANCE=new BonusHandler();
        return INSTANCE;
    }

    public void tick() {
        if (!bonuses.entrySet().isEmpty()) {
            bonusCountdown();            //starting bonus timer
        }

    }

    public void render(Graphics g) {
        if (!bonuses.entrySet().isEmpty()) {
            player = handler.getPlayer();
            bonuses.forEach((k, v) -> {
                switch (k.getBonusType()) {                   //setting visual bonus effects
                    case NAJMAN -> {
                        g.setColor(Color.BLUE);
                        g.setFont(HUD.smallFont);
                        g.drawString("Weź sie rozpędź i jebnij łbem o ścianę", (int) player.getX(), (int) player.getY() - 20);
                    }
                    case PUDZIAN -> {
                        g.setColor(Color.pink);
                        g.setFont(HUD.smallFont);
                        g.drawString("POLSKA GUROM!1!1!", (int) player.getX(), (int) player.getY() - 20);
                    }
                    case MICHAEL_JACKSON -> {
                        g.setColor(Color.WHITE);
                        g.setFont(HUD.smallFont);
                        g.drawString("Moonwalk", (int) player.getX(), (int) player.getY() - 20);

                    }
                    case PIZZA -> {
                        g.setColor(Color.ORANGE);
                        g.setFont(HUD.smallFont);
                        g.drawString("Mamma mia, molto grande pizzerinia", (int) player.getX(), (int) player.getY() - 20);

                    }
                    case ILLUMINATI -> {
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setComposite(makeTransparent(transparency));
                        if (v > 3)
                            transparency += 0.00001f;
                        else if (transparency > 0.00003f)
                            transparency -= 0.00003f;
                        g2d.drawImage(Game.illuminati_image, 254, 200, Game.GAME_WIDTH -560, Game.GAME_HEIGHT -400, null);
                        g2d.setComposite(makeTransparent(1));
                    }
                }
            });
        }

    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    public void addBonus(Bonus bonus) {         //adding bonusInterface with its timer to map
        this.bonuses.put(bonus, 10);
        bonusAction(bonus);
    }

    private void bonusAction(Bonus bonus) {     //bonusInterface start
        bonus.bonusPower();
    }

    private void bonusCountdown() {
        counter++;
        if (counter >= 30) {
            counter = 0;
            // bonuses.forEach((k, v) -> this.trueCounter = v);
            bonuses.forEach((k, v) -> bonuses.put(k, v - 1));      //decrementing bonus timer every thirty ticks
            bonuses.forEach((k, v) -> {
                if (v == 0) {
                    k.bonusDepower();                          //bonus end when timer is 0
                    transparency = 0.0000001f;
                }

            });
            bonuses.entrySet().removeIf(b -> b.getValue() == 0);       //removing bonus from map if timer is 0

        }
    }

    public void removeBonuses() {
        //bonuses.clear();
        bonuses.values().clear();
    }


}
