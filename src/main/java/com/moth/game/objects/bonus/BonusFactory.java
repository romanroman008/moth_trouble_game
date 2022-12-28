package com.moth.game.objects.bonus;

import com.moth.game.Game;
import com.moth.game.enums.BonusType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class BonusFactory {

    private static final double BONUS_START_X_POSITION = -100;
    private static final double BONUS_START_Y_POSITION = Game.GAME_HEIGHT / 2 - 100;
    private static final double BONUS_START_X_VELOCITY = 5;
    private static final double BONUS_START_Y_VELOCITY = 0;

    private static final double MICHAEL_JACKSON_HEIGHT = 70;
    private static final double MICHAEL_JACKSON_WIDTH = 30;

    private static final double PUDZIAN_HEIGHT = 70;
    private static final double PUDZIAN_WIDTH = 50;

    private static final double NAJMAN_HEIGHT = 70;
    private static final double NAJMAN_WIDTH = 40;

    private static final double ILLUMINATI_HEIGHT = 60;
    private static final double ILLUMINATI_WIDTH = 100;

    private static final double PIZZA_HEIGHT = 50;
    private static final double PIZZA_WIDTH = 50;


    public static Bonus createBonus(BonusType bonusType) {
        switch (bonusType) {
            case MICHAEL_JACKSON -> {
                return new MichaelJackson(
                        BONUS_START_X_POSITION,
                        BONUS_START_Y_POSITION,
                        MICHAEL_JACKSON_HEIGHT,
                        MICHAEL_JACKSON_WIDTH,
                        BONUS_START_X_VELOCITY,
                        BONUS_START_Y_VELOCITY,
                        bonusType);
            }
            case PUDZIAN -> {
                return new MariuszPudzian(
                        BONUS_START_X_POSITION,
                        BONUS_START_Y_POSITION,
                        PUDZIAN_HEIGHT,
                        PUDZIAN_WIDTH,
                        BONUS_START_X_VELOCITY,
                        BONUS_START_Y_VELOCITY,
                        bonusType);
            }
            case NAJMAN -> {
                return new NajmanMarcin(
                        BONUS_START_X_POSITION,
                        BONUS_START_Y_POSITION,
                        NAJMAN_HEIGHT,
                        NAJMAN_WIDTH,
                        BONUS_START_X_VELOCITY,
                        BONUS_START_Y_VELOCITY,
                        bonusType);
            }
            case ILLUMINATI -> {
                return new Illuminati(
                        BONUS_START_X_POSITION,
                        BONUS_START_Y_POSITION,
                        ILLUMINATI_HEIGHT,
                        ILLUMINATI_WIDTH,
                        BONUS_START_X_VELOCITY,
                        BONUS_START_Y_VELOCITY,
                        bonusType);
            }
            case PIZZA -> {
                return new Pizza(
                        BONUS_START_X_POSITION,
                        BONUS_START_Y_POSITION,
                        PIZZA_HEIGHT,
                        PIZZA_WIDTH,
                        BONUS_START_X_VELOCITY,
                        BONUS_START_Y_VELOCITY,
                        bonusType);
            }
            default ->
            throw new IllegalArgumentException("Wrong bonus type");

        }
    }

    public static List<Bonus> createBonusList(){
       return Arrays.stream(BonusType.values())
               .map(BonusFactory::createBonus)
               .collect(Collectors.toList());
    }


}
