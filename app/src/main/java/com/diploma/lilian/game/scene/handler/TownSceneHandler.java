package com.diploma.lilian.game.scene.handler;

import android.content.Context;
import android.util.DisplayMetrics;

import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.game.provider.BaseSpriteProvider;
import com.diploma.lilian.game.provider.TownSceneProvider;
import com.diploma.lilian.game.scene.TownScene;

public class TownSceneHandler extends BaseSceneHandler<TownScene> {

    public TownSceneHandler(Context context, DisplayMetrics metrics, Player player) {
        super(context, metrics, player);

        scene = new TownScene(this.context,metrics.widthPixels, metrics.heightPixels);

        BaseSpriteProvider spriteProvider = new TownSceneProvider(context, player);
        scene.setSpriteProvider(spriteProvider);

        scene.start();

    }



}
