package com.diploma.lilian.game.provider;

import android.content.Context;

import com.diploma.lilian.database.datamanager.PlayerDataManager;
import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.engine.io.SpriteDataParser;
import com.diploma.lilian.game.CollisionType;
import com.diploma.lilian.game.data.Enemy;
import com.diploma.lilian.game.scene.TestScene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class DefaultSpriteProvider extends BaseSpriteProvider {

    public DefaultSpriteProvider(Context context, PlayerDataManager playerDataManager) {
        super(context, playerDataManager);
    }

    @Override
    public void loadSprites() {
        IsoSprite grass = null;
        try {
            grass = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/grass.xml"));
            grass.setWidth(TestScene.WORLD_WIDTH);
            grass.setHeight(TestScene.WORLD_HEIGHT);
            grass.moveInPlot(0, 0, 0);
            grass.setAnimation("copy");


        } catch (IOException e) {
            e.printStackTrace();
        }

        spriteInfoCollection.add(new SpriteInfo(grass, TestScene.GROUND_LAYER));

        IsoSprite sprite = null;
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

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/kard.xml"));
            sprite.setAnimation("simple");
            sprite.moveInPlot(0, 100, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TestScene.MAIN_LAYER));

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
        playerIsoSprite.moveInPlot(1200, 700, 0);
        playerIsoSprite.setAnimation("left_down_move");
        playerIsoSprite.setAnimationStartFrame(0);

        playerIsoSprite.setMoveAnimationNames(new String[]{"left_move", "left_up_move", "up_move", "right_up_move",
                "right_move", "right_down_move", "down_move", "left_down_move"});

        playerIsoSprite.moveInIso(1, 0, 0);
        playerIsoSprite.addCollisionType(CollisionType.PLAYER_ENEMY.getValue());

        playerSpriteInfo = new SpriteInfo(playerIsoSprite, TestScene.MAIN_LAYER, playerDataManager.getPlayer());
        return playerSpriteInfo;
    }


    @Override
    public Collection<SpriteInfo> getEnemiesSpriteInfo() {
        if(enemiesSpriteInfoCollection!=null)
            return enemiesSpriteInfoCollection;

        enemiesSpriteInfoCollection = new ArrayList<>();
        Random random = new Random();
        IsoSprite mushroom = null;
        for (int i = 0; i < 10; i++) {
            try {
                mushroom = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/mushroom.xml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mushroom.moveInPlot(random.nextInt(TestScene.WORLD_WIDTH), random.nextInt(TestScene.WORLD_HEIGHT), 0);
            mushroom.setAnimation("stand");
            mushroom.setAnimationStartFrame(0);

            mushroom.setMoveAnimationNames(new String[]{"stand","fight"});

            mushroom.moveInIso(1, 0, 0);
            mushroom.addCollisionType(CollisionType.PLAYER_ENEMY.getValue());

            enemiesSpriteInfoCollection.add(new SpriteInfo(mushroom, TestScene.MAIN_LAYER, new Enemy(1)));
        }

        return enemiesSpriteInfoCollection;
    }
}
