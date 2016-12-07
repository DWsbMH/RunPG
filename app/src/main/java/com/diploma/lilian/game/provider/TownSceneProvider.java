package com.diploma.lilian.game.provider;

import android.content.Context;

import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.engine.io.SpriteDataParser;
import com.diploma.lilian.game.scene.TownScene;

import java.io.IOException;
import java.util.Collection;

public class TownSceneProvider extends BaseSpriteProvider {
    public TownSceneProvider(Context context, Player player) {
        super(context, player);
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

        for(int i = 0; i<1600;i+=120) {
            try {
                sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/route.xml"));
                sprite.setAnimation("default");
                sprite.moveInPlot(i, 1912, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));
        }

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/route.xml"));
            sprite.setAnimation("default");
            sprite.moveInPlot(1560, 1612, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));
        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/route.xml"));
            sprite.setAnimation("default");
            sprite.moveInPlot(1560, 1712, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));
        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/route.xml"));
            sprite.setAnimation("default");
            sprite.moveInPlot(1560, 1812, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/route.xml"));
            sprite.setAnimation("default");
            sprite.moveInPlot(700, 1812, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));

        for(int i = 622; i<4112; i+=120){
            try {
                sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/route.xml"));
                sprite.setAnimation("default");
                sprite.moveInPlot(1560, i, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));

        }

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/well.xml"));
            sprite.setAnimation("default");
            sprite.moveInPlot(1400, 2012, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/templom.xml"));
            sprite.moveInPlot(0, 512, 0);
            sprite.addCollisionType(CollisionType.PLAYER_BUILDING.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/own_house.xml"));
            sprite.moveInPlot(0, 1536, 0);
            sprite.addCollisionType(CollisionType.PLAYER_BUILDING.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/kocsma.xml"));
            sprite.moveInPlot(1500, 512, 0);
            sprite.addCollisionType(CollisionType.PLAYER_BUILDING.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/fegyverbolt.xml"));
            sprite.moveInPlot(1536, 1536, 0);
            sprite.addCollisionType(CollisionType.PLAYER_BUILDING.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, TownScene.MAIN_LAYER));

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/gates.xml"));
            sprite.setAnimation("default");
            sprite.moveInPlot(1500, 512, 0);
            sprite.addCollisionType(CollisionType.PLAYER_BUILDING.getValue());
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
        playerIsoSprite.moveInPlot(2500, 2500, 0);
        playerIsoSprite.setAnimation("left_down_move");
        playerIsoSprite.setAnimationStartFrame(0);

        playerIsoSprite.setMoveAnimationNames(new String[]{"left_move", "left_up_move", "up_move", "right_up_move",
                "right_move", "right_down_move", "down_move", "left_down_move"});

        playerIsoSprite.moveInIso(1, 0, 0);
        playerIsoSprite.addCollisionType(CollisionType.PLAYER_ENEMY.getValue());
        playerIsoSprite.addCollisionType(CollisionType.PLAYER_BUILDING.getValue());

        playerSpriteInfo = new SpriteInfo(playerIsoSprite, TownScene.MAIN_LAYER, player);
        return playerSpriteInfo;
    }
}
