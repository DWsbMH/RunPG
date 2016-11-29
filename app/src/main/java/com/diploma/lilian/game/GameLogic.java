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

public class GameLogic implements OnFightListener, OnLevelUpListener {

    private Context context;

    private final PlayerDataManager playerDataManager;
    private Player player;

    private final DisplayMetrics metrics;
    private final OnGameListener listener;

    private BattleFieldSceneHandler battleFieldSceneHandler;
    private FightSceneHandler fightSceneHandler;
    private TownSceneHandler townSceneHandler;

    public GameLogic(Context context, DisplayMetrics metrics, OnGameListener listener) {

        /* TODO STAMINA */

        this.context = context;
        this.metrics = metrics;
        this.listener = listener;

        playerDataManager = new PlayerDataManager(context);
        player = playerDataManager.getPlayer();
    }

    private void initHandlers() {
        battleFieldSceneHandler = new BattleFieldSceneHandler(context, metrics, player);
        battleFieldSceneHandler.setOnFightListener(this);

        fightSceneHandler = new FightSceneHandler(context, metrics, player);
        fightSceneHandler.setOnLevelUpListener(this);
        townSceneHandler = new TownSceneHandler(context, metrics, player);
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
        battleFieldSceneHandler.getScene().removeEnemy();
        battleFieldSceneHandler.getScene().resetCollisionDetector();
        listener.switchSceneTo(battleFieldSceneHandler.getScene(), battleFieldSceneHandler.getScene().getHUD());
    }

    @Override
    public void onFightLost() {
        /* TODO place player on town with 10-20% health, maybe some penalty*/
    }

    public void startFight() {
        fightSceneHandler.startFight();
    }

    public void startGame() {
        initHandlers();
//        listener.switchSceneTo(townSceneHandler.getScene(), townSceneHandler.getHUD());
        listener.switchSceneTo(battleFieldSceneHandler.getScene(), battleFieldSceneHandler.getHUD());
        battleFieldSceneHandler.getScene().resetCollisionDetector();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void levelUpTo(int newLevel) {
        player.getAttributes().setLevel(newLevel);
        // TODO player stat change according to level -> use Formulas in Player class
    }

    public void savePlayer() {
        playerDataManager.update(player);
    }

    public interface OnGameListener {
        void switchSceneTo(BaseScene sceneToSwitch, Fragment HUD);
    }


}
