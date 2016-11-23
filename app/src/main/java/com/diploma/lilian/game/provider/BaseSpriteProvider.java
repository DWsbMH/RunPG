package com.diploma.lilian.game.provider;

import android.content.Context;

import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.engine.io.SpriteDataParser;
import com.diploma.lilian.game.scene.BattleFieldScene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseSpriteProvider implements ISpriteProvider {

    final Player player;
    protected Context context;

    Collection<SpriteInfo> spriteInfoCollection;
    Collection<SpriteInfo> enemiesSpriteInfoCollection;
    SpriteInfo playerSpriteInfo;

    BaseSpriteProvider(Context context, Player player) {
        this.context = context;
        this.spriteInfoCollection = new ArrayList<>();
        this.player = player;
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
            playerIsoSprite.setAnimation("left_down_move");
            playerIsoSprite.setAnimationStartFrame(0);

            playerIsoSprite.setMoveAnimationNames(new String[]{"left_move", "left_up_move", "up_move", "right_up_move",
                    "right_move", "right_down_move", "down_move", "left_down_move"});

            playerIsoSprite.moveInIso(1, 0, 0);
            playerIsoSprite.addCollisionType(CollisionType.PLAYER_ENEMY.getValue());
            playerIsoSprite.addCollisionType(CollisionType.PLAYER_BUILDING.getValue());

        } catch (IOException e) {
            e.printStackTrace();
        }
        playerSpriteInfo = new SpriteInfo(playerIsoSprite, BattleFieldScene.MAIN_LAYER, player);
        return playerSpriteInfo;

    }

}
