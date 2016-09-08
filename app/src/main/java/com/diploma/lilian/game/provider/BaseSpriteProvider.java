package com.diploma.lilian.game.provider;

import android.content.Context;

import com.diploma.lilian.database.datamanager.PlayerDataManager;
import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.engine.io.SpriteDataParser;
import com.diploma.lilian.game.CollisionType;
import com.diploma.lilian.game.scene.TestScene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseSpriteProvider implements ISpriteProvider {

    protected Context context;

    protected SpriteInfo playerSpriteInfo;
    protected Collection<SpriteInfo> spriteInfoCollection;
    private PlayerDataManager playerDataManager;

    public BaseSpriteProvider(Context context) {
        this.context = context;
        this.spriteInfoCollection = new ArrayList<>();
        this.playerDataManager = new PlayerDataManager(this.context);
        loadSprites();
    }

    public Collection<SpriteInfo> getSpriteInfoCollection() {
        return spriteInfoCollection;
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

        playerSpriteInfo = new SpriteInfo(playerIsoSprite, TestScene.MAIN_LAYER, new com.diploma.lilian.game.data.Player(playerDataManager.getPlayer()));
        return playerSpriteInfo;
    }

}
