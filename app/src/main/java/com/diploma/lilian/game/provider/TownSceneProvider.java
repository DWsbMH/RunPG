package com.diploma.lilian.game.provider;

import android.content.Context;

import com.diploma.lilian.database.datamanager.PlayerDataManager;
import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.engine.io.SpriteDataParser;
import com.diploma.lilian.game.scene.TownScene;

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
            sprite.setWidth(TownScene.WORLD_WIDTH);
            sprite.setHeight(TownScene.WORLD_HEIGHT);
            sprite.moveInPlot(0, 0, 0);
            sprite.setAnimation("copy");


        } catch (IOException e) {
            e.printStackTrace();
        }

        spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.GROUND_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/own_house.xml"));
            sprite.moveInPlot(0, 0, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/templom.xml"));
            sprite.moveInPlot(0, 1024, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/kocsma.xml"));
            sprite.moveInPlot(1024, 0, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/fegyverbolt.xml"));
            sprite.moveInPlot(1024, 1024, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));

    }

    @Override
    public SpriteInfo getPlayerSpriteInfo() {
        if (playerSpriteInfo != null)
            return playerSpriteInfo;

        IsoSprite playerIsoSprite = null;
        try {
            playerIsoSprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/male_light_sprite.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        playerIsoSprite.moveInPlot(1200, 1200, 0);
        playerIsoSprite.setAnimation("left_down_move");
        playerIsoSprite.setAnimationStartFrame(0);

        playerIsoSprite.setMoveAnimationNames(new String[]{"left_move", "left_up_move", "up_move", "right_up_move",
                "right_move", "right_down_move", "down_move", "left_down_move"});

        playerIsoSprite.moveInIso(1, 0, 0);
        playerIsoSprite.addCollisionType(CollisionType.PLAYER_ENEMY.getValue());

        playerSpriteInfo = new SpriteInfo(playerIsoSprite, TownScene.MAIN_LAYER, playerDataManager.getPlayer());
        return playerSpriteInfo;
    }
}
