package com.diploma.lilian.game.scene.handler;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;

import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.game.scene.BaseScene;

abstract class BaseSceneHandler<T extends BaseScene> {
    protected Context context;
    private Fragment HUD;
    DisplayMetrics metrics;
    protected final Player player;
    protected T scene;

    BaseSceneHandler(Context context, DisplayMetrics metrics, Player player) {
        this.context = context;
        this.metrics = metrics;
        this.player = player;
    }

    public T getScene() {
        return scene;
    }

    public Fragment getHUD() {
        if(HUD == null){
            HUD = scene.getHUD();
        }
        return HUD;
    }
}
