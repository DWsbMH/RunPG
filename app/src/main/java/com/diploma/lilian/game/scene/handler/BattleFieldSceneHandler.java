package com.diploma.lilian.game.scene.handler;

import android.content.Context;
import android.util.DisplayMetrics;

import com.diploma.lilian.database.datamanager.BattleFieldDataManager;
import com.diploma.lilian.database.entity.BattleField;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.Sprite;
import com.diploma.lilian.game.OnFightListener;
import com.diploma.lilian.game.OnPlayerListener;
import com.diploma.lilian.game.provider.BaseSpriteProvider;
import com.diploma.lilian.game.provider.BattleFieldSpriteProvider;
import com.diploma.lilian.game.provider.SpriteInfo;
import com.diploma.lilian.game.scene.BattleFieldScene;
import com.j256.ormlite.dao.ForeignCollection;

public class BattleFieldSceneHandler extends BaseSceneHandler<BattleFieldScene>{

    BattleFieldDataManager battleFieldDataManager;
    BattleField battleField;

    public BattleFieldSceneHandler(Context context, DisplayMetrics metrics, Player player) {
        super(context, metrics, player);

        scene = new BattleFieldScene(context, this.metrics.widthPixels, this.metrics.heightPixels);

        BaseSpriteProvider spriteProvider = new BattleFieldSpriteProvider(context, player);
        scene.setSpriteProvider(spriteProvider);

        scene.start();

        battleFieldDataManager = new BattleFieldDataManager(context);
        battleField = battleFieldDataManager.queryForAll().get(0);

    }

    public void setOnPlayerListener(OnPlayerListener onPlayerListener) {
//        scene.setOnPlayerListener(onPlayerListener);
    }

    public void setOnFightListener(OnFightListener onFightListener) {
        scene.setOnFightListener(onFightListener);
    }

    public void updatePlayer() {
//        scene.updatePlayer(playerDataManager.getPlayer());
    }

    public void removeEnemy() {
        SpriteInfo enemy = getScene().getEnemy();

        ForeignCollection<Sprite> enemies = battleField.getSprites();
        for(Sprite sprite : enemies){
            if(sprite.getId() == enemy.getData().getId()){
                sprite.setBattleField(null);
                battleField.getSprites().remove(sprite);
            }
        }

        battleFieldDataManager.add(battleField);

        getScene().removeEnemy();

    }
}
