package com.diploma.lilian.game.provider;

import android.content.Context;

import com.diploma.lilian.database.datamanager.PlayerDataManager;
import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.engine.io.SpriteDataParser;
import com.diploma.lilian.game.scene.TestScene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseSpriteProvider implements ISpriteProvider {

    final PlayerDataManager playerDataManager;
    protected Context context;

    Collection<SpriteInfo> spriteInfoCollection;
    Collection<SpriteInfo> enemiesSpriteInfoCollection;
    SpriteInfo playerSpriteInfo;

    BaseSpriteProvider(Context context, PlayerDataManager playerDataManager) {
        this.context = context;
        this.spriteInfoCollection = new ArrayList<>();
        this.playerDataManager = playerDataManager;
    }

    public Collection<SpriteInfo> getSpriteInfoCollection() {

        loadSprites();

        return spriteInfoCollection;
    }

    @Override
    public SpriteInfo getPlayerSpriteInfo() {
        if(playerSpriteInfo != null)
            return playerSpriteInfo;

        IsoSprite playerIsoSprite = null;
        try {
            playerIsoSprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/male_light_sprite.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        playerSpriteInfo = new SpriteInfo(playerIsoSprite, TestScene.MAIN_LAYER, playerDataManager.getPlayer());
        return playerSpriteInfo;

    }

}
