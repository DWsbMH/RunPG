package com.diploma.lilian.game.util;

import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.SportActivity;
import com.diploma.lilian.game.data.CreatureData;

import java.util.Random;

public class Formulas {

    private static final float A = 8.7f;
    private static final float B = -40;
    private static final float C = 111;

    private static final Random random = new Random();

    public static long experienceForNextLevel(int actualLevel){
        return (long)(Math.exp((actualLevel - B) / A) - C) + 30;
    }

    public static long experienceForEnemy(CreatureData enemy){
        return (long) ((35 + enemy.getLevel()*enemy.getLevel()*enemy.getLevel()*(random.nextFloat() * (1.2 - 0.8) + 0.8) ) / 5);
    }

    public static int staminaForSportActivity(SportActivity sportActivity) {

        int stamina = 0;

        switch (sportActivity.getActivityType()){
            case RUNNING:
                stamina = (int) (8 *(sportActivity.getTotalDistance()/1000)/(sportActivity.getDuration()/3600));
                break;
            case HIKING:
                stamina = (int) (6 *(sportActivity.getTotalDistance()/1000)/(sportActivity.getDuration()/3600));
                break;
            case CROSSFIT:
                stamina = 12 *(sportActivity.getDuration()/3600);
                break;
            case CYCLING:
                stamina = (int) (2 *(sportActivity.getTotalDistance()/1000)/(sportActivity.getDuration()/3600));
                break;
            case YOGA:
                stamina = 15 *(sportActivity.getDuration()/3600);
                break;
            case SNOWBOARDING:
                stamina = (int) (10 *(sportActivity.getTotalDistance()/1000)/(sportActivity.getDuration()/3600));
                break;
            case WALKING:
                stamina = (int) (5 *(sportActivity.getTotalDistance()/1000)/(sportActivity.getDuration()/3600));
                break;
            case SWIMMING:
                stamina = (int) (15 *(sportActivity.getTotalDistance()/1000)/(sportActivity.getDuration()/3600));
                break;
            case SKATING:
                stamina = 20 *(sportActivity.getDuration()/3600);
                break;
            case OTHER:
                stamina = 10 *(sportActivity.getDuration()/3600);
                break;
        }

        return stamina;

    }

    public static int getRewardWeaponDamage(int playerLevel){
        return (int) (Math.log10( Math.pow(3,playerLevel) )* ((3*playerLevel)/(Math.log10(3))));

    }

    public static int getMaxHealth(Player player){
        return player.getAttributes().getEndurance() * 5 * (player.getLevel() + 1)+100;
    }

    public static int getDamage(Player player){
        int averageDamage = (player.getPlayerSheet().getWeapon().getMaxDamage() +
                player.getPlayerSheet().getWeapon().getMinDamage()) / 2;
        int strength = player.getAttributes().getStrength();
        if (player.getPlayerSheet().getStrength() != null){
            strength *= player.getPlayerSheet().getStrength().getEffect().getEffect();
        }
        return averageDamage * (1 + strength / 10);
    }

}
