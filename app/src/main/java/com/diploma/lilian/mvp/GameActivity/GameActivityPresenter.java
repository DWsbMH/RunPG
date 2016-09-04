package com.diploma.lilian.mvp.GameActivity;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import com.diploma.lilian.database.datamanager.PlayerDataManager;
import com.diploma.lilian.game.OnFightListener;
import com.diploma.lilian.game.OnPlayerListener;
import com.diploma.lilian.game.entity.Enemy;
import com.diploma.lilian.game.entity.Player;
import com.diploma.lilian.game.scene.FightScene;
import com.diploma.lilian.game.scene.TestScene;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

public class GameActivityPresenter extends MvpBasePresenter<GameActivityView> implements OnPlayerListener, OnFightListener {

    private TestScene actualGameScene;
    private FightScene fightScene;

    private Context context;
    private final DisplayMetrics metrics;
    private PlayerDataManager playerDataManager;

    public GameActivityPresenter(Context context) {
        this.context = context;
        metrics = new DisplayMetrics();
        playerDataManager = new PlayerDataManager(this.context);
    }

    public void init() {
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        if (isViewAttached()) {
            actualGameScene = new TestScene(context, metrics.widthPixels, metrics.heightPixels, playerDataManager.getPlayer());
            actualGameScene.setOnPlayerListener(this);
            actualGameScene.setOnFightListener(this);
            getView().setGameScene(actualGameScene);
        }
    }

    @Override
    public void setHealth(int health) {
        if (isViewAttached()) {
            actualGameScene.getPlayer().getData().setActualHealthPoint(health);
            getView().setHealth(health);
        }
    }

    @Override
    public void fight(Player player, Enemy enemy) {
        // TODO set new gamescene
        if (isViewAttached()) {
            fightScene = new FightScene(context, metrics.widthPixels, metrics.heightPixels,player,enemy);
            getView().setGameScene(fightScene);
        }
    }

    @Override
    public void onFightWin() {
        actualGameScene.removeEnemy(fightScene.getEnemy());
    }

    public TestScene getActualGameScene() {
        return actualGameScene;
    }

    public void startFight() {
        // TODO fight with enemy
        onFightWin();
    }
}
