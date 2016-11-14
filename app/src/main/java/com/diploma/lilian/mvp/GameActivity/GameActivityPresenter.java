package com.diploma.lilian.mvp.GameActivity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;

import com.diploma.lilian.game.OnEnemyListener;
import com.diploma.lilian.game.OnFightListener;
import com.diploma.lilian.game.OnPlayerListener;
import com.diploma.lilian.game.provider.SpriteInfo;
import com.diploma.lilian.game.scene.handler.DefaultSceneHandler;
import com.diploma.lilian.game.scene.handler.FightSceneHandler;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

public class GameActivityPresenter extends MvpBasePresenter<GameActivityView> implements OnFightListener{

    private DefaultSceneHandler defaultSceneHandler;
    private FightSceneHandler fightSceneHandler;

    private Context context;
    private DisplayMetrics metrics;

    public GameActivityPresenter(Context context) {
        this.context = context;
        metrics = new DisplayMetrics();
    }

    public void init(DisplayMetrics metrics) {
        this.metrics = metrics;
        defaultSceneHandler = new DefaultSceneHandler(context, metrics);
        fightSceneHandler = new FightSceneHandler(context, metrics);

        if (isViewAttached()) {
//            getView().setMaxHealth(defaultSceneHandler.getScene().getPlayer().getData().getMaxHealthPoint());
//            getView().setPlayerHealth(defaultSceneHandler.getScene().getPlayer().getData().getActualHealthPoint());
            defaultSceneHandler.setOnFightListener(this);
            getView().setGameScene(defaultSceneHandler.getScene(),defaultSceneHandler.getScene().getHUD());
        }


    }

    @Override
    public void fightAgainst(SpriteInfo enemy) {
        // TODO set new gamescene
        if (isViewAttached()) {
            fightSceneHandler.setEnemy(enemy);
            fightSceneHandler.initFightScene();
            Fragment hud = fightSceneHandler.getScene().getHUD();
            fightSceneHandler.setEnemyListener((OnEnemyListener) hud);
            fightSceneHandler.setPlayerListener((OnPlayerListener) hud);
            fightSceneHandler.setOnFightListener(this);
            getView().setGameScene(fightSceneHandler.getScene(), hud);
        }
    }

    @Override
    public void onFightWin() {
/*
        defaultSceneHandler.getScene().getPlayer().getData().setActualHealthPoint(100);
        getView().setPlayerHealth(100);
*/
        defaultSceneHandler.updatePlayer();
        defaultSceneHandler.getScene().removeEnemy();
        defaultSceneHandler.getScene().resetCollisionDetector();
        getView().setGameScene(defaultSceneHandler.getScene(),defaultSceneHandler.getScene().getHUD());
    }

    public void startFight() {
        // TODO fightAgainst with enemy
        fightSceneHandler.startFight();
    }

}
