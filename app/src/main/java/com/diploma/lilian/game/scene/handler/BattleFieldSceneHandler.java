package com.diploma.lilian.game.scene.handler;

import android.content.Context;
import android.util.DisplayMetrics;

import com.diploma.lilian.game.OnFightListener;
import com.diploma.lilian.game.OnPlayerListener;
import com.diploma.lilian.game.provider.BaseSpriteProvider;
import com.diploma.lilian.game.provider.BattleFieldSpriteProvider;
import com.diploma.lilian.game.scene.BattleFieldScene;

public class BattleFieldSceneHandler extends BaseSceneHandler<BattleFieldScene>{

    public BattleFieldSceneHandler(Context context, DisplayMetrics metrics) {
        super(context, metrics);

        scene = new BattleFieldScene(context, this.metrics.widthPixels, this.metrics.heightPixels);

        BaseSpriteProvider spriteProvider = new BattleFieldSpriteProvider(context, playerDataManager);
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
