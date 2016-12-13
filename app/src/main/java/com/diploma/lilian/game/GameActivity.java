package com.diploma.lilian.game;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;

import com.diploma.lilian.engine.GLCanvas;
import com.diploma.lilian.game.fragment.FightFragment;
import com.diploma.lilian.game.fragment.InventoryFragment;
import com.diploma.lilian.game.fragment.RewardFragment;
import com.diploma.lilian.game.fragment.ShopFragment;
import com.diploma.lilian.game.scene.BaseScene;
import com.diploma.lilian.game.scene.FightScene;
import com.diploma.lilian.mvp.GameActivity.GameActivityPresenter;
import com.diploma.lilian.mvp.GameActivity.GameActivityView;
import com.diploma.lilian.runpg.BaseActivity;
import com.diploma.lilian.runpg.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends BaseActivity<GameActivityView, GameActivityPresenter> implements GameActivityView, FightFragment.OnFightFragmentListener, InventoryFragment.OnInventoryListener, RewardFragment.OnRewardListener, OnShopListener {

    @BindView(R.id.glcanvas)
    GLCanvas canvas;
    @BindView(R.id.inventory_open)
    ImageButton openInventoryButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        presenter.startGame();
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
            getSupportFragmentManager().beginTransaction().replace(R.id.hud_container, fragment).commit();
        }
    }

    @NonNull
    @Override
    public GameActivityPresenter createPresenter() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return new GameActivityPresenter(this, metrics);
    }

    @Override
    public void fight() {
        presenter.startFight();
    }

    @OnClick(R.id.inventory_open)
    public void showInventory() {
        openInventoryButton.setVisibility(View.GONE);
        ((BaseScene) canvas.getGameScene()).setInventoryOpen(true);
        setFragment(InventoryFragment.newInstance("", ""));
    }


    @Override
    public void onInventoryClose() {
        openInventoryButton.setVisibility(View.VISIBLE);
        ((BaseScene) canvas.getGameScene()).setInventoryOpen(false);
        Fragment hud = ((BaseScene) canvas.getGameScene()).getHUD();
        if (canvas.getGameScene() instanceof FightScene) {
            presenter.resetFightListeners(hud);
        }
        setFragment(hud);
    }

    @Override
    public void onRewardOpen() {
        setFragment(new RewardFragment());
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onRewardListClose() {
        setFragment(InventoryFragment.newInstance("", ""));
    }

    public void inventoryOnClick(View view) { }

    @Override
    public void onShopEnter(final String shopType) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                openInventoryButton.setVisibility(View.GONE);
                setFragment(ShopFragment.newInstance(shopType));
            }
        });
    }

    @Override
    public void onShopExit() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                openInventoryButton.setVisibility(View.VISIBLE);
                setFragment(new Fragment());
            }
        });
    }
}
