package com.diploma.lilian.game.provider;

import android.content.Context;

import com.diploma.lilian.database.datamanager.PlayerDataManager;
import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.engine.io.SpriteDataParser;
import com.diploma.lilian.game.data.Enemy;
import com.diploma.lilian.game.scene.BattleFieldScene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class BattleFieldSpriteProvider extends BaseSpriteProvider {

    public BattleFieldSpriteProvider(Context context, PlayerDataManager playerDataManager) {
        super(context, playerDataManager);
    }

    @Override
    public void loadSprites() {
        IsoSprite sprite = null;

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/grass.xml"));
            sprite.setWidth(BattleFieldScene.WORLD_WIDTH);
            sprite.setHeight(BattleFieldScene.WORLD_HEIGHT);
            sprite.moveInPlot(0, 0, 0);
            sprite.setAnimation("copy");


        } catch (IOException e) {
            e.printStackTrace();
        }

        spriteInfoCollection.add(new SpriteInfo(sprite, BattleFieldScene.GROUND_LAYER));

        for(int i = 0; i<1600;i+=120) {
            try {
                sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/route.xml"));
                sprite.setAnimation("default");
                sprite.moveInPlot(i, 1400, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            spriteInfoCollection.add(new SpriteInfo(sprite, BattleFieldScene.MAIN_LAYER));
        }

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/route.xml"));
            sprite.setAnimation("default");
            sprite.moveInPlot(1560, 1100, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, BattleFieldScene.MAIN_LAYER));
        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/route.xml"));
            sprite.setAnimation("default");
            sprite.moveInPlot(1560, 1200, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, BattleFieldScene.MAIN_LAYER));
        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/route.xml"));
            sprite.setAnimation("default");
            sprite.moveInPlot(1560, 1300, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, BattleFieldScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/route.xml"));
            sprite.setAnimation("default");
            sprite.moveInPlot(700, 1300, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, BattleFieldScene.MAIN_LAYER));

        for(int i = 1420; i<3600; i+=120){
            try {
                sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/route.xml"));
                sprite.setAnimation("default");
                sprite.moveInPlot(1560, i, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            spriteInfoCollection.add(new SpriteInfo(sprite, BattleFieldScene.MAIN_LAYER));

        }

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/well.xml"));
            sprite.setAnimation("default");
            sprite.moveInPlot(1400, 1500, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, BattleFieldScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/templom.xml"));
            sprite.moveInPlot(0, 0, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, BattleFieldScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/own_house.xml"));
            sprite.moveInPlot(0, 1024, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, BattleFieldScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/kocsma.xml"));
            sprite.moveInPlot(1500, 0, 0);
            sprite.addCollisionType(CollisionType.PLAYER_BUILDING.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, BattleFieldScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/fegyverbolt.xml"));
            sprite.moveInPlot(1536, 1024, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, BattleFieldScene.MAIN_LAYER));

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
        playerIsoSprite.addCollisionType(CollisionType.PLAYER_BUILDING.getValue());

        playerSpriteInfo = new SpriteInfo(playerIsoSprite, BattleFieldScene.MAIN_LAYER, playerDataManager.getPlayer());
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
            mushroom.moveInPlot(random.nextInt(BattleFieldScene.WORLD_WIDTH), random.nextInt(BattleFieldScene.WORLD_HEIGHT), 0);
            mushroom.setAnimation("stand");
            mushroom.setAnimationStartFrame(0);

            mushroom.setMoveAnimationNames(new String[]{"stand","fight"});

            mushroom.moveInIso(1, 0, 0);
            mushroom.addCollisionType(CollisionType.PLAYER_ENEMY.getValue());

            enemiesSpriteInfoCollection.add(new SpriteInfo(mushroom, BattleFieldScene.MAIN_LAYER, new Enemy(1)));
        }

        return enemiesSpriteInfoCollection;
    }
}
