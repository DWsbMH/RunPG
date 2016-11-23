package com.diploma.lilian.game.util;

import com.diploma.lilian.game.data.CreatureData;

import java.util.Random;

public class Formulas {

    private static final float A = 8.7f;
    private static final float B = -40;
    private static final float C = 111;

    private static final Random random = new Random();

    public static long experienceForNextLevel(int actualLevel){
        return (long)(Math.exp((actualLevel - B) / A) - C);
    }

    public static long experienceForEnemy(CreatureData enemy){
        return (long) ((35 + enemy.getLevel()*enemy.getLevel()*enemy.getLevel()*(random.nextFloat() * (1.2 - 0.8) + 0.8) ) / 5);
    }

}
