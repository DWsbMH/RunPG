package com.diploma.lilian.game.provider;

import android.content.Context;

import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.engine.io.SpriteDataParser;
import com.diploma.lilian.game.CollisionType;
import com.diploma.lilian.game.data.Enemy;
import com.diploma.lilian.game.scene.TestScene;

import java.io.IOException;
import java.util.Random;

public class DefaultSpriteProvider extends BaseSpriteProvider {

    public DefaultSpriteProvider(Context context) {
        super(context);
    }

    @Override
    public void loadSprites() {
        IsoSprite mushroom = null;
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            try {
                mushroom = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/mushroom.xml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mushroom.moveInPlot(random.nextInt(TestScene.WORLD_WIDTH), random.nextInt(TestScene.WORLD_HEIGHT), 0);
            mushroom.setAnimation("stand");
            mushroom.setAnimationStartFrame(0);

            mushroom.setMoveAnimationNames(new String[]{"stand"});

            mushroom.moveInIso(1, 0, 0);
            mushroom.addCollisionType(CollisionType.PLAYER_ENEMY.getValue());

            spriteInfoCollection.add(new SpriteInfo(mushroom, TestScene.MAIN_LAYER, new Enemy(1)));
        }

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

    }
}
