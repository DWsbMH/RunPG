package com.diploma.lilian.game;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.widget.Button;

import com.diploma.lilian.engine.GLCanvas;
import com.diploma.lilian.engine.GameScene;
import com.diploma.lilian.mvp.GameActivity.GameActivityPresenter;
import com.diploma.lilian.mvp.GameActivity.GameActivityView;
import com.diploma.lilian.runpg.R;
import com.diploma.lilian.view.BarView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends MvpActivity<GameActivityView, GameActivityPresenter> implements GameActivityView {

    @BindView(R.id.glcanvas)
    GLCanvas canvas;
    @BindView(R.id.switchButton)
    Button switchButton;
    @BindView(R.id.healthBar)
    BarView healthBar;
    @BindView(R.id.fight)
    Button fightButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        presenter.init(metrics);

    }

    @Override
    public void setGameScene(final GameScene gameScene) {
        canvas.setGameScene(null);
        canvas.setGameScene(gameScene);
        canvas.setFps(100);
    }

    @Override
    public void setHealth(final int health) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                healthBar.setActualPoint(health);
            }
        });
    }

    @NonNull
    @Override
    public GameActivityPresenter createPresenter() {
        return new GameActivityPresenter(this);
    }

    @OnClick(R.id.switchButton)
    public void switchScene() {
        presenter.getActualGameScene().resetCollisionDetector();
        setGameScene(presenter.getActualGameScene());
    }

    @OnClick(R.id.fight)
    public void fight(){
        presenter.startFight();
    }

}
