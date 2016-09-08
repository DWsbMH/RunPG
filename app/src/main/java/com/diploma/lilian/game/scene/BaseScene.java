package com.diploma.lilian.game.scene;

import android.content.Context;

import com.diploma.lilian.engine.GameScene;

public abstract class BaseScene extends GameScene{
    protected Context context;

    public BaseScene(Context context, int surfaceWidth, int surfaceHeight) {
        super(context, surfaceWidth, surfaceHeight);
        this.context = context;
    }

    public void start(){
        initialized();
        init(this.context);
    }
}
