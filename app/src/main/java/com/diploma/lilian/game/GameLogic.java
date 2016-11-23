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

public class GameLogic implements OnFightListener {

    private Context context;
    private Player player;
    private final DisplayMetrics metrics;
    private final OnGameListener listener;

    private BattleFieldSceneHandler battleFieldSceneHandler;
    private FightSceneHandler fightSceneHandler;
    private TownSceneHandler townSceneHandler;

    public GameLogic(Context context, DisplayMetrics metrics, OnGameListener listener) {
        this.context = context;
        player = new PlayerDataManager(context).getPlayer();
        this.metrics = metrics;
        this.listener = listener;
    }

    private void initHandlers() {
        battleFieldSceneHandler = new BattleFieldSceneHandler(context, metrics);
        battleFieldSceneHandler.setOnFightListener(this);

        fightSceneHandler = new FightSceneHandler(context, metrics);
        townSceneHandler = new TownSceneHandler(context, metrics);
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

    public interface OnGameListener {
        void switchSceneTo(BaseScene sceneToSwitch, Fragment HUD);
    }

}
