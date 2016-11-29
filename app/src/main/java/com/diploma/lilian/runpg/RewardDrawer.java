package com.diploma.lilian.runpg;

import android.content.Context;

import com.diploma.lilian.database.datamanager.PotionDataManager;
import com.diploma.lilian.database.datamanager.WeaponDataManager;
import com.diploma.lilian.database.entity.Backpack;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.Potion;
import com.diploma.lilian.database.entity.PotionType;
import com.diploma.lilian.database.entity.SportActivity;
import com.diploma.lilian.database.entity.Weapon;
import com.diploma.lilian.game.util.Formulas;
import com.diploma.lilian.tracker.SportActivityType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RewardDrawer {

    private final Context context;
    private final Player player;
    private Random random;
    private Map<SportActivityType, RewardItem[]> rewardChance;
    private Backpack backpack;
    private WeaponDataManager weaponDataManager;
    private PotionDataManager potionDataManager;
    private String[] weapons;

    public RewardDrawer(Context context, Player player) {
        this.context = context;
        this.player = player;
        backpack = player.getBackpack();
        weaponDataManager = new WeaponDataManager(context);
        potionDataManager = new PotionDataManager(context);

        weapons = new String[7];
        weapons[0] = "hosszukard";
        weapons[1] = "hosszutor";
        weapons[2] = "kard";
        weapons[3] = "katana";
        weapons[4] = "kes";
        weapons[5] = "szablya";
        weapons[6] = "tor";

        rewardChance = new HashMap<>();
        RewardItem[] runningHikingCrossFit = new RewardItem[3];
        runningHikingCrossFit[0] = new RewardItem(10, RewardType.REWARD_GOLD);
        runningHikingCrossFit[1] = new RewardItem(30, RewardType.REWARD_POTION);
        runningHikingCrossFit[2] = new RewardItem(60, RewardType.REWARD_WEAPON);
        rewardChance.put(SportActivityType.RUNNING, runningHikingCrossFit);
        rewardChance.put(SportActivityType.HIKING, runningHikingCrossFit);
        rewardChance.put(SportActivityType.CROSSFIT, runningHikingCrossFit);

        RewardItem[] cycleYogaSnowboard = new RewardItem[3];
        cycleYogaSnowboard[0] = new RewardItem(10, RewardType.REWARD_POTION);
        cycleYogaSnowboard[1] = new RewardItem(30, RewardType.REWARD_WEAPON);
        cycleYogaSnowboard[2] = new RewardItem(60, RewardType.REWARD_GOLD);
        rewardChance.put(SportActivityType.CYCLING, cycleYogaSnowboard);
        rewardChance.put(SportActivityType.YOGA, cycleYogaSnowboard);
        rewardChance.put(SportActivityType.SNOWBOARDING, cycleYogaSnowboard);

        RewardItem[] walkSwimSkate = new RewardItem[3];
        walkSwimSkate[0] = new RewardItem(10, RewardType.REWARD_WEAPON);
        walkSwimSkate[1] = new RewardItem(30, RewardType.REWARD_GOLD);
        walkSwimSkate[2] = new RewardItem(60, RewardType.REWARD_POTION);
        rewardChance.put(SportActivityType.WALKING, walkSwimSkate);
        rewardChance.put(SportActivityType.SWIMMING, walkSwimSkate);
        rewardChance.put(SportActivityType.SKATING, walkSwimSkate);


        random = new Random();
    }

    public void checkForGift(SportActivity sportActivity) {

        int stamina = Formulas.staminaForSportActivity(sportActivity);
        float actualStamina = player.getAttributes().getActualStamina();
        if (stamina + actualStamina <= 100)
            player.getAttributes().setActualStamina(stamina + actualStamina);
        else
            player.getAttributes().setActualStamina(100);

        if (Requirements.check(sportActivity)) {
            int chance = random.nextInt(100);
            double cumulativeProbability = 0.0;
            RewardItem[] items = rewardChance.get(sportActivity.getActivityType());
            for (RewardItem item : items) {
                cumulativeProbability += item.getProbability();
                if (chance <= cumulativeProbability) {
                    addRewardToPlayer(item.getType());
                }
            }

        }

    }

    private void addRewardToPlayer(RewardType type) {
        switch (type) {
            case REWARD_WEAPON:

                int weapontemp = random.nextInt(weapons.length);

                Weapon weapon = new Weapon(backpack, weapons[weapontemp], weapons[weapontemp],
                        Formulas.getRewardWeaponDamage(player.getLevel()),
                        Formulas.getRewardWeaponDamage(player.getLevel() + 1));
                weaponDataManager.add(weapon);

                break;
            case REWARD_POTION:
                String name = null;
                String imageName = null;
                PotionType potionType = null;

                int temp = random.nextInt(4) + 1;
                if (temp == 1) {
                    potionType = PotionType.HEALTH;
                    name = "health";
                    imageName = "large_health";
                }
                if (temp == 2) {
                    potionType = PotionType.LUCK;
                    name = "luck";
                    imageName = "large_luck";
                }
                if (temp == 3) {
                    potionType = PotionType.STRENGTH;
                    name = "strength";
                    imageName = "large_strength";
                }
                if (temp == 4) {
                    potionType = PotionType.ENDURANCE;
                    name = "endurance";
                    imageName = "large_endurance";
                }

                Potion potion = new Potion(backpack, name, imageName, 6, potionType);
                potionDataManager.add(potion);

                break;
            case REWARD_GOLD:

                player.setGold(player.getGold() + 1200);

                break;
        }

    }

    private class RewardItem {
        int probability;
        RewardType type;

        RewardItem(int probability, RewardType type) {
            this.probability = probability;
            this.type = type;
        }

        int getProbability() {
            return probability;
        }

        public RewardType getType() {
            return type;
        }
    }

    private enum RewardType {

        REWARD_WEAPON, REWARD_POTION, REWARD_GOLD;

    }


}
