package com.diploma.lilian.game.scene.handler;

import android.content.Context;
import android.util.DisplayMetrics;

public abstract class BaseSceneHandler<T> {
    protected Context context;
    protected DisplayMetrics metrics;
    protected T scene;

    public BaseSceneHandler(Context context, DisplayMetrics metrics) {
        this.context = context;
        this.metrics = metrics;
    }

    public T getScene() {
        return scene;
    }
}
