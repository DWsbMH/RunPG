package com.diploma.lilian.game.scene.handler;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;

import com.diploma.lilian.database.datamanager.PlayerDataManager;
import com.diploma.lilian.game.scene.BaseScene;

abstract class BaseSceneHandler<T extends BaseScene> {
    protected Context context;
    Fragment HUD;
    DisplayMetrics metrics;
    protected T scene;
    PlayerDataManager playerDataManager;

    BaseSceneHandler(Context context, DisplayMetrics metrics) {
        this.context = context;
        this.metrics = metrics;
        this.playerDataManager = new PlayerDataManager(this.context);
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
