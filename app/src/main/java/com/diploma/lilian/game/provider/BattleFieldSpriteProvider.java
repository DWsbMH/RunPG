package com.diploma.lilian.game.provider;

import android.content.Context;

import com.diploma.lilian.database.datamanager.BattleFieldDataManager;
import com.diploma.lilian.database.entity.BattleField;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.Sprite;
import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.engine.io.SpriteDataParser;
import com.diploma.lilian.game.data.Enemy;
import com.diploma.lilian.game.scene.BattleFieldScene;
import com.diploma.lilian.game.util.BattleFieldGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BattleFieldSpriteProvider extends BaseSpriteProvider {

    private BattleFieldDataManager battleFieldDataManager;

    public BattleFieldSpriteProvider(Context context, Player player) {
        super(context, player);
        battleFieldDataManager = new BattleFieldDataManager(context);
        enemiesSpriteInfoCollection = new ArrayList<>();
    }

    @Override
    public void loadSprites() {
        List<BattleField> temp = battleFieldDataManager.queryForAll();
        BattleField battleField;
        if (temp.isEmpty()) {
            battleField = BattleFieldGenerator.generate(context, 1);
            System.out.println();
        } else {
            battleField = temp.get(0);
        }

        for (Sprite sprite : battleField.getSprites()) {
            if (sprite.getEnemyData() == null)
                spriteInfoCollection.add(getSpriteInfo(sprite));
            else {
                enemiesSpriteInfoCollection.add(getSpriteInfo(sprite));
            }
        }

        System.out.println();
/*
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

        try {
            sprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/gates.xml"));
            sprite.setAnimation("default");
            sprite.moveInPlot(BattleFieldScene.WORLD_WIDTH / 2, BattleFieldScene.WORLD_HEIGHT / 2, 0);
            sprite.addCollisionType(CollisionType.PLAYER_BUILDING.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteInfoCollection.add(new SpriteInfo(sprite, BattleFieldScene.MAIN_LAYER));
*/

    }


    private SpriteInfo getSpriteInfo(Sprite sprite) {
        IsoSprite isoSprite = null;
        SpriteInfo spriteInfo;
        try {
            isoSprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/" + sprite.getName() + ".xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        isoSprite.setAnimation("default");

        if (sprite.getLayer() == BattleFieldScene.GROUND_LAYER) {
            isoSprite.setWidth(BattleFieldScene.WORLD_WIDTH);
            isoSprite.setHeight(BattleFieldScene.WORLD_HEIGHT);
            isoSprite.setAnimation("copy");
        }

        isoSprite.moveInPlot(sprite.getX(), sprite.getY(), 0);
        isoSprite.addCollisionType(sprite.getCollisionType().getValue());

        if (sprite.getEnemyData() != null) {
            isoSprite.setAnimation("stand");
            isoSprite.setAnimationStartFrame(0);
            isoSprite.moveInIso(1, 0, 0);

            isoSprite.setMoveAnimationNames(new String[]{"stand", "fight"});
            spriteInfo = new SpriteInfo(isoSprite, sprite.getLayer(), new Enemy(sprite.getEnemyData().getLevel(), sprite.getId()));

        } else {
            spriteInfo = new SpriteInfo(isoSprite, sprite.getLayer());
        }


        return spriteInfo;
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
        playerIsoSprite.moveInPlot(BattleFieldScene.WORLD_WIDTH / 2, BattleFieldScene.WORLD_HEIGHT / 2, 0);
        playerIsoSprite.setAnimation("left_down_move");
        playerIsoSprite.setAnimationStartFrame(0);

        playerIsoSprite.setMoveAnimationNames(new String[]{"left_move", "left_up_move", "up_move", "right_up_move",
                "right_move", "right_down_move", "down_move", "left_down_move"});

        playerIsoSprite.moveInIso(1, 0, 0);
        playerIsoSprite.addCollisionType(CollisionType.PLAYER_ENEMY.getValue());
        playerIsoSprite.addCollisionType(CollisionType.PLAYER_BUILDING.getValue());

        playerSpriteInfo = new SpriteInfo(playerIsoSprite, BattleFieldScene.MAIN_LAYER, player);
        return playerSpriteInfo;
    }


    @Override
    public Collection<SpriteInfo> getEnemiesSpriteInfo() {
/*
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
*/

        return enemiesSpriteInfoCollection;
    }
}
