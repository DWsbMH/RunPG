package com.diploma.lilian.game;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.diploma.lilian.engine.GLCanvas;
import com.diploma.lilian.game.fragment.FightFragment;
import com.diploma.lilian.game.fragment.InventoryFragment;
import com.diploma.lilian.game.scene.BaseScene;
import com.diploma.lilian.mvp.GameActivity.GameActivityPresenter;
import com.diploma.lilian.mvp.GameActivity.GameActivityView;
import com.diploma.lilian.runpg.BaseActivity;
import com.diploma.lilian.runpg.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends BaseActivity<GameActivityView, GameActivityPresenter> implements GameActivityView, FightFragment.OnFragmentInteractionListener, InventoryFragment.OnInventoryListener {

    @BindView(R.id.glcanvas)
    GLCanvas canvas;
    @BindView(R.id.inventory_open)
    Button openInventoryButton;

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
    public void setGameScene(BaseScene gameScene, Fragment hud) {
        canvas.setGameScene(null);
        setFragment(hud);
        canvas.setGameScene(gameScene);
        canvas.setFps(100);
    }

    @Override
    public void setFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }

    @NonNull
    @Override
    public GameActivityPresenter createPresenter() {
        return new GameActivityPresenter(this);
    }

    @Override
    public void fight() {
        presenter.startFight();
    }

    @OnClick(R.id.inventory_open)
    public void showInventory(){
        openInventoryButton.setVisibility(View.GONE);
        setFragment(InventoryFragment.newInstance("",""));
    }


    @Override
    public void onInventoryClose() {
        openInventoryButton.setVisibility(View.VISIBLE);
        setFragment(((BaseScene)canvas.getGameScene()).getHUD());
    }
}
