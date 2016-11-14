package com.diploma.lilian.game.scene;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.diploma.lilian.engine.GameScene;
import com.diploma.lilian.game.provider.BaseSpriteProvider;

public abstract class BaseScene extends GameScene{
    protected Context context;
    protected BaseSpriteProvider spriteProvider;

    public BaseScene(Context context, int surfaceWidth, int surfaceHeight) {
        super(context, surfaceWidth, surfaceHeight);
        this.context = context;
    }

    public void start(){
        initialized();
        init(this.context);
    }


    public void setSpriteProvider(BaseSpriteProvider spriteProvider) {
        this.spriteProvider = spriteProvider;
    }

    public abstract Fragment getHUD();


}
