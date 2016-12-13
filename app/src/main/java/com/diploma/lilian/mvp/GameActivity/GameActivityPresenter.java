package com.diploma.lilian.mvp.GameActivity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;

import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.game.GameLogic;
import com.diploma.lilian.game.scene.BaseScene;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

public class GameActivityPresenter extends MvpBasePresenter<GameActivityView> implements GameLogic.OnGameListener {

    private GameLogic gameLogic;

    public GameActivityPresenter(Context context, DisplayMetrics metrics) {
        gameLogic = new GameLogic(context, metrics, this);
    }

    public void startFight() {
        gameLogic.startFight();
    }

    @Override
    public void switchSceneTo(BaseScene sceneToSwitch, Fragment HUD) {
        if (isViewAttached()) {
            getView().setGameScene(sceneToSwitch, HUD);
        }
    }

    public void startGame() {
        gameLogic.startGame();
    }

    public Player getPlayer() {
        return gameLogic.getPlayer();
    }

    public void onPause() {
        gameLogic.savePlayer();
    }

    public void resetFightListeners(Fragment hud) {
        gameLogic.resetFightListeners(hud);
    }
}
