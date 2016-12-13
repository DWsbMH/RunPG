package com.diploma.lilian.game;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;

import com.diploma.lilian.database.datamanager.PlayerDataManager;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.game.fragment.FightFragment;
import com.diploma.lilian.game.provider.SpriteInfo;
import com.diploma.lilian.game.scene.BaseScene;
import com.diploma.lilian.game.scene.handler.BattleFieldSceneHandler;
import com.diploma.lilian.game.scene.handler.FightSceneHandler;
import com.diploma.lilian.game.scene.handler.TownSceneHandler;
import com.diploma.lilian.game.util.Formulas;

    public class GameLogic implements OnFightListener, OnLevelUpListener, OnGateListener {

    private Context context;

    private final PlayerDataManager playerDataManager;
    private Player player;

    private final DisplayMetrics metrics;
    private final OnGameListener listener;

    private BattleFieldSceneHandler battleFieldSceneHandler;
    private FightSceneHandler fightSceneHandler;
    private TownSceneHandler townSceneHandler;

    public static final String SCENE_BATTLEFIELD = "BATTLEFIELD";
    public static final String SCENE_TOWN = "TOWN";

    public GameLogic(Context context, DisplayMetrics metrics, OnGameListener listener) {

        this.context = context;
        this.metrics = metrics;
        this.listener = listener;

        playerDataManager = PlayerDataManager.INSTANCE(context);
        player = playerDataManager.getPlayer();
    }

    private void initHandlers() {
        battleFieldSceneHandler = new BattleFieldSceneHandler(context, metrics, player);
        battleFieldSceneHandler.setOnFightListener(this);
        battleFieldSceneHandler.setOnGateListener(this);

        fightSceneHandler = new FightSceneHandler(context, metrics, player);
        fightSceneHandler.setOnLevelUpListener(this);

        townSceneHandler = new TownSceneHandler(context, metrics, player);
        townSceneHandler.setGateListener(this);
        townSceneHandler.setShopListener((OnShopListener) context);
    }


    @Override
    public void fightAgainst(SpriteInfo enemy) {
        fightSceneHandler.setEnemy(enemy);
        fightSceneHandler.initFightScene();
        FightFragment hud = (FightFragment) fightSceneHandler.getScene().getHUD();
        fightSceneHandler.setEnemyListener(hud);
        fightSceneHandler.setPlayerListener(hud);
        fightSceneHandler.setOnFightListener(this);
        listener.switchSceneTo(fightSceneHandler.getScene(), hud);
    }

    @Override
    public void onFightWin() {
        battleFieldSceneHandler.updatePlayer();
        battleFieldSceneHandler.getScene().resetCollisionDetector();
        int remainEnemy = battleFieldSceneHandler.removeEnemy();
        if (remainEnemy == 0) {

            battleFieldSceneHandler.generateBattleField();

            townSceneHandler.getScene().resetCollisionDetector();
            listener.switchSceneTo(townSceneHandler.getScene(), townSceneHandler.getScene().getHUD());
            townSceneHandler.getScene().getViewport().move(0,0);
            townSceneHandler.getScene().getViewport().move((int)townSceneHandler.getScene().getPlayer().getSprite().getX(),
                    (int)townSceneHandler.getScene().getPlayer().getSprite().getY());
            player.setLastScene(SCENE_TOWN);
        } else {
            listener.switchSceneTo(battleFieldSceneHandler.getScene(), battleFieldSceneHandler.getScene().getHUD());
            battleFieldSceneHandler.getScene().getViewport().move(0,0);
            battleFieldSceneHandler.getScene().getViewport().move((int)battleFieldSceneHandler.getScene().getPlayer().getSprite().getX(),
                    (int)battleFieldSceneHandler.getScene().getPlayer().getSprite().getY());
            player.setLastScene(SCENE_BATTLEFIELD);
        }
        afterBattleTasks();
    }

    @Override
    public void onFightLost() {
        afterBattleTasks();
        player.getPlayerSheet().deleteEndurancePotion();
        player.getPlayerSheet().deleteStrengthPotion();
        player.getPlayerSheet().deleteLuckPotion();
        player.setLastScene(SCENE_TOWN);
        player.setActualHealthPoint(player.getMaxHealthPoint());
//        townSceneHandler.getScene().getPlayer().getSprite().moveTo(700,1812,0,2500);
        townSceneHandler.getScene().resetCollisionDetector();
        listener.switchSceneTo(townSceneHandler.getScene(), townSceneHandler.getScene().getHUD());
        townSceneHandler.getScene().getViewport().move(0,0);
        townSceneHandler.getScene().getViewport().move((int)townSceneHandler.getScene().getPlayer().getSprite().getX(),
                (int)townSceneHandler.getScene().getPlayer().getSprite().getY());

        /* TODO place player on town with 10-20% health, maybe some penalty*/
    }

    private void afterBattleTasks() {
        if (player.getPlayerSheet().getEndurance() != null) {
            player.getPlayerSheet().getEndurance().getEffect().decreaseDurability();
            if (player.getPlayerSheet().getEndurance().getEffect().getDurability() == 0) {
                player.getPlayerSheet().deleteEndurancePotion();
            }
        }
        if (player.getPlayerSheet().getStrength() != null) {
            if (player.getPlayerSheet().getStrength() != null) {
                player.getPlayerSheet().getStrength().getEffect().decreaseDurability();
                if (player.getPlayerSheet().getStrength().getEffect().getDurability() == 0) {
                    player.getPlayerSheet().deleteStrengthPotion();
                }
            }

        }
        if (player.getPlayerSheet().getLuck() != null) {
            if (player.getPlayerSheet().getLuck() != null) {
                player.getPlayerSheet().getLuck().getEffect().decreaseDurability();
                if (player.getPlayerSheet().getLuck().getEffect().getDurability() == 0) {
                    player.getPlayerSheet().deleteLuckPotion();
                }
            }
        }
    }

    public void startFight() {
        fightSceneHandler.startFight();
    }

    public void startGame() {
        initHandlers();

        if (player.getLastScene() == null || player.getLastScene().equals(SCENE_TOWN)) {
            listener.switchSceneTo(townSceneHandler.getScene(), townSceneHandler.getHUD());
            townSceneHandler.getScene().getViewport().move(0,0);
            townSceneHandler.getScene().getViewport().move((int)townSceneHandler.getScene().getPlayer().getSprite().getX(),
                    (int)townSceneHandler.getScene().getPlayer().getSprite().getY());
        } else if (player.getLastScene().equals(SCENE_BATTLEFIELD)) {
            if (player.getLastXPosition() != -1 && player.getLastYPosition() != -1) {
                battleFieldSceneHandler.getScene().getPlayer().getSprite().setX(player.getLastXPosition());
                battleFieldSceneHandler.getScene().getPlayer().getSprite().setY(player.getLastYPosition());
            }
            battleFieldSceneHandler.getScene().resetCollisionDetector();
            listener.switchSceneTo(battleFieldSceneHandler.getScene(), battleFieldSceneHandler.getHUD());
            battleFieldSceneHandler.getScene().getViewport().move(0,0);
            battleFieldSceneHandler.getScene().getViewport().move((int)battleFieldSceneHandler.getScene().getPlayer().getSprite().getX(),
                    (int)townSceneHandler.getScene().getPlayer().getSprite().getY());
        }
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void levelUpTo(int newLevel) {
        player.getAttributes().setLevel(newLevel);
        player.getAttributes().setFreePoints(4);
        player.getAttributes().setExperienceNeeded((int) Formulas.experienceForNextLevel(newLevel));
    }

    public void savePlayer() {
        playerDataManager.update(player);
    }

    @Override
    public void onGateCollision(String scene) {
        if (scene.equals(SCENE_TOWN)) {

            battleFieldSceneHandler.getScene().resetCollisionDetector();
            listener.switchSceneTo(battleFieldSceneHandler.getScene(), battleFieldSceneHandler.getHUD());
            battleFieldSceneHandler.getScene().getViewport().move(0,0);
            battleFieldSceneHandler.getScene().getViewport().move((int)battleFieldSceneHandler.getScene().getPlayer().getSprite().getX(),
                    (int)townSceneHandler.getScene().getPlayer().getSprite().getY());
            player.setLastScene(SCENE_BATTLEFIELD);
        } else if (scene.equals(SCENE_BATTLEFIELD)) {
            townSceneHandler.getScene().resetCollisionDetector();
            listener.switchSceneTo(townSceneHandler.getScene(), townSceneHandler.getHUD());
            townSceneHandler.getScene().getViewport().move(0,0);
            townSceneHandler.getScene().getViewport().move((int)townSceneHandler.getScene().getPlayer().getSprite().getX(),
                    (int)townSceneHandler.getScene().getPlayer().getSprite().getY());
            player.setLastScene(SCENE_TOWN);
        }


    }

    public void resetFightListeners(Fragment hud) {
        fightSceneHandler.setPlayerListener((FightFragment) hud);
        fightSceneHandler.setEnemyListener((FightFragment) hud);
    }

    public interface OnGameListener {
        void switchSceneTo(BaseScene sceneToSwitch, Fragment HUD);
    }


}
