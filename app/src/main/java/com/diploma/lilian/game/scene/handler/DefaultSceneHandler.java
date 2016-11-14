package com.diploma.lilian.game.scene.handler;

import android.content.Context;
import android.util.DisplayMetrics;

import com.diploma.lilian.game.OnFightListener;
import com.diploma.lilian.game.OnPlayerListener;
import com.diploma.lilian.game.provider.BaseSpriteProvider;
import com.diploma.lilian.game.provider.DefaultSpriteProvider;
import com.diploma.lilian.game.scene.TestScene;

public class DefaultSceneHandler extends BaseSceneHandler<TestScene>{

    public DefaultSceneHandler(Context context, DisplayMetrics metrics) {
        super(context, metrics);

        scene = new TestScene(context, this.metrics.widthPixels, this.metrics.heightPixels);

        BaseSpriteProvider spriteProvider = new DefaultSpriteProvider(context, playerDataManager);
        scene.setSpriteProvider(spriteProvider);

        scene.start();
    }

    public void setOnPlayerListener(OnPlayerListener onPlayerListener) {
//        scene.setOnPlayerListener(onPlayerListener);
    }

    public void setOnFightListener(OnFightListener onFightListener) {
        scene.setOnFightListener(onFightListener);
    }

    public void updatePlayer() {
        scene.updatePlayer(playerDataManager.getPlayer());
    }
}
