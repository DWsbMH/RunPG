package com.diploma.lilian.game.scene.handler;

import android.content.Context;
import android.util.DisplayMetrics;

import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.game.OnEnemyListener;
import com.diploma.lilian.game.OnFightListener;
import com.diploma.lilian.game.OnFightTurnListener;
import com.diploma.lilian.game.OnPlayerListener;
import com.diploma.lilian.game.provider.BaseSpriteProvider;
import com.diploma.lilian.game.provider.FightSpriteProvider;
import com.diploma.lilian.game.provider.SpriteInfo;
import com.diploma.lilian.game.scene.FightScene;
import com.diploma.lilian.game.util.Formulas;

public class FightSceneHandler extends BaseSceneHandler<FightScene> implements OnFightTurnListener {

    private SpriteInfo enemy;
    private OnEnemyListener onEnemyListener;
    private OnPlayerListener onPlayerListener;

    private boolean fightStarted = false;

    private SpriteInfo attacker;
    private SpriteInfo defender;
    private OnFightListener onFightListener;


    public FightSceneHandler(Context context, DisplayMetrics metrics) {
        super(context, metrics);
    }

    public void initFightScene() {
        BaseSpriteProvider provider = new FightSpriteProvider(context, playerDataManager, metrics, enemy);
        scene = new FightScene(context, metrics.widthPixels, metrics.heightPixels, provider);
        scene.setOnFightTurnListener(this);
        scene.start();
    }

    public void setEnemy(SpriteInfo enemy) {
        this.enemy = enemy;
    }

    public void startFight() {
        fightStarted = true;
        attacker = scene.getPlayer();
        defender = scene.getEnemy();
    }

    public void setEnemyListener(OnEnemyListener onEnemyListener) {
        this.onEnemyListener = onEnemyListener;
    }

    public void setPlayerListener(OnPlayerListener onPlayerListener) {
        this.onPlayerListener = onPlayerListener;
    }

    @Override
    public void update() {
        if (fightStarted) {
            if (!defender.getSprite().isAnimationRunning()) {
                SpriteInfo temp;

                attacker.getSprite().setAnimation("fight");
                attacker.getSprite().setLoopAnimation(false);
                attacker.getSprite().setAnimationStartFrame(0);
                attacker.getSprite().startAnimation();

                int remainHealth = defender.getData().getActualHealthPoint() - attacker.getData().getDamage();
                defender.getData().setActualHealthPoint(remainHealth);

                updateHealth(defender);

                if (defender.getData().getActualHealthPoint() < 0) {
                    if (defender.equals(scene.getPlayer())) {
                        onFightListener.onFightLost();
                    } else {
                        Player player = (Player) scene.getPlayer().getData();
                        int experienceForEnemy = (int) (player.getAttributes().getExperienceGained() + Formulas.experienceForEnemy(
                                getScene().getEnemy().getData()));

                        if (player.getAttributes().getExperienceGained() + experienceForEnemy >=
                                player.getAttributes().getExperienceNeeded()) {
                            int experienceDiff = (player.getAttributes().getExperienceGained() + experienceForEnemy -
                                    player.getAttributes().getExperienceNeeded());
                            player.getAttributes().setExperienceGained(experienceDiff);
//                            onPlayerListener.onLevelUpTo(player.getAttributes().getLevel());
                        } else {
                            player.getAttributes().setExperienceGained(experienceForEnemy);
                        }
                        playerDataManager.update(player);
                        onFightListener.onFightWin();
                    }
                }

                temp = attacker;
                attacker = defender;
                defender = temp;

            } else {
                attacker.getSprite().getLayer().setDirty(true);
            }
        }
    }

    private void updateHealth(SpriteInfo defender) {
        if (defender.equals(scene.getPlayer())) {
            onPlayerListener.setPlayerHealth(defender.getData().getActualHealthPoint());
        } else {
            onEnemyListener.setEnemyHealth(defender.getData().getActualHealthPoint());
        }
    }

    public void setOnFightListener(OnFightListener onFightListener) {
        this.onFightListener = onFightListener;
    }
}
