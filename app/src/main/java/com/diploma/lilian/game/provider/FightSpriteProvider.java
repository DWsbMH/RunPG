package com.diploma.lilian.game.provider;

import android.content.Context;
import android.util.DisplayMetrics;

import com.diploma.lilian.database.datamanager.PlayerDataManager;
import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.engine.io.SpriteDataParser;
import com.diploma.lilian.game.CollisionType;
import com.diploma.lilian.game.scene.TestScene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class FightSpriteProvider extends BaseSpriteProvider {

    private final DisplayMetrics metrics;
    private final SpriteInfo enemy;
    private Collection<SpriteInfo> returnEnemies;

    public FightSpriteProvider(Context context, PlayerDataManager playerDataManager, DisplayMetrics metrics, SpriteInfo enemy) {
        super(context, playerDataManager);
        this.metrics = metrics;
        this.enemy = enemy;
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
    }

    @Override
    public Collection<SpriteInfo> getEnemiesSpriteInfo() {
        if(enemiesSpriteInfoCollection != null)
            return enemiesSpriteInfoCollection;

        enemiesSpriteInfoCollection = new ArrayList<>();
        IsoSprite enemyIsoSprite = null;
        try {
            enemyIsoSprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/" + enemy.getSprite().getName() + ".xml"));
            enemyIsoSprite.moveInPlot(metrics.widthPixels - 2 * enemyIsoSprite.getWidth(), metrics.heightPixels / 2 - enemyIsoSprite.getHeight(), 0.0f);
            enemyIsoSprite.setAnimation("stand");
            enemyIsoSprite.setAnimationStartFrame(0);

            enemyIsoSprite.setMoveAnimationNames(new String[]{"stand","fight"});

            enemyIsoSprite.moveInIso(1, 0, 0);
            enemyIsoSprite.addCollisionType(1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        enemiesSpriteInfoCollection.add(new SpriteInfo(enemyIsoSprite, TestScene.MAIN_LAYER, enemy.getData()));
        return enemiesSpriteInfoCollection;
    }

    @Override
    public SpriteInfo getPlayerSpriteInfo() {
        playerSpriteInfo = super.getPlayerSpriteInfo();

        playerSpriteInfo.getSprite().moveInPlot(0, metrics.widthPixels/2, 0);
        playerSpriteInfo.getSprite().setAnimation("right_move");
        playerSpriteInfo.getSprite().setAnimationStartFrame(0);

        playerSpriteInfo.getSprite().setMoveAnimationNames(new String[]{"left_move", "left_up_move", "up_move", "right_up_move",
                "right_move", "right_down_move", "down_move", "left_down_move", "fight"});

        playerSpriteInfo.getSprite().moveInIso(1, 0, 0);
        playerSpriteInfo.getSprite().addCollisionType(CollisionType.PLAYER_ENEMY.getValue());

        return playerSpriteInfo;
    }

}
