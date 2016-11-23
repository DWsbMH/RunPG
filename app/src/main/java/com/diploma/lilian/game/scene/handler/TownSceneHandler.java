package com.diploma.lilian.game.scene.handler;

import android.content.Context;
import android.util.DisplayMetrics;

import com.diploma.lilian.game.provider.BaseSpriteProvider;
import com.diploma.lilian.game.provider.TownSceneProvider;
import com.diploma.lilian.game.scene.TownScene;

public class TownSceneHandler extends BaseSceneHandler<TownScene> {

    public TownSceneHandler(Context context, DisplayMetrics metrics) {
        super(context, metrics);

        scene = new TownScene(this.context,metrics.widthPixels, metrics.heightPixels);

        BaseSpriteProvider spriteProvider = new TownSceneProvider(context, playerDataManager);
        scene.setSpriteProvider(spriteProvider);

        scene.start();

    }



}
