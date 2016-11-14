package com.diploma.lilian.game.provider;

import android.content.Context;

import com.diploma.lilian.database.datamanager.PlayerDataManager;
import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.engine.io.SpriteDataParser;
import com.diploma.lilian.game.scene.TestScene;

import java.io.IOException;
import java.util.Collection;

public class TownSceneProvider extends BaseSpriteProvider {
    public TownSceneProvider(Context context, PlayerDataManager playerDataManager) {
        super(context, playerDataManager);
    }

    @Override
    public Collection<SpriteInfo> getEnemiesSpriteInfo() {
        return null;
    }

    @Override
    public void loadSprites() {
        IsoSprite sprite = null;
        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/grass.xml"));
            sprite.setWidth(TestScene.WORLD_WIDTH);
            sprite.setHeight(TestScene.WORLD_HEIGHT);
            sprite.moveInPlot(0, 0, 0);
            sprite.setAnimation("copy");


        } catch (IOException e) {
            e.printStackTrace();
        }

        spriteInfoCollection.add(new SpriteInfo(sprite, TestScene.GROUND_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/own_house.xml"));
            sprite.moveInPlot(0, 0, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TestScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/templom.xml"));
            sprite.moveInPlot(0, 1024, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TestScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/kocsma.xml"));
            sprite.moveInPlot(1024, 0, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TestScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/fegyverbolt.xml"));
            sprite.moveInPlot(1024, 1024, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TestScene.MAIN_LAYER));

    }
}
