package com.diploma.lilian.mvp.GameActivity;

import android.content.Context;
import android.util.DisplayMetrics;

import com.diploma.lilian.game.scene.handler.DefaultSceneHandler;
import com.diploma.lilian.game.OnFightListener;
import com.diploma.lilian.game.OnPlayerListener;
import com.diploma.lilian.game.provider.SpriteInfo;
import com.diploma.lilian.game.scene.FightScene;
import com.diploma.lilian.game.scene.TestScene;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

public class GameActivityPresenter extends MvpBasePresenter<GameActivityView> implements OnPlayerListener, OnFightListener {

    private DefaultSceneHandler defaultSceneHandler;
    private FightScene fightScene;

    private Context context;
    private DisplayMetrics metrics;

    public GameActivityPresenter(Context context) {
        this.context = context;
        metrics = new DisplayMetrics();
    }

    public void init(DisplayMetrics metrics) {
        this.metrics = metrics;
        if (isViewAttached()) {
            defaultSceneHandler = new DefaultSceneHandler(context, metrics);
            defaultSceneHandler.setOnFightListener(this);
            getView().setGameScene(defaultSceneHandler.getScene());
        }
    }

    @Override
    public void setHealth(int health) {
        if (isViewAttached()) {
//            defaultSceneHandler.getScene().getPlayer().getData().setActualHealthPoint(health);
            getView().setHealth(health);
        }
    }

    @Override
    public void fight(SpriteInfo player, SpriteInfo enemy) {
        // TODO set new gamescene
        if (isViewAttached()) {
            fightScene = new FightScene(context, metrics.widthPixels, metrics.heightPixels,player,enemy);
            fightScene.start();
            getView().setGameScene(fightScene);
        }
    }

    @Override
    public void onFightWin() {
        defaultSceneHandler.getScene().removeEnemy();
    }

    public TestScene getActualGameScene() {
        return defaultSceneHandler.getScene();
    }

    public void startFight() {
        // TODO fight with enemy
        onFightWin();
    }
}
