package com.diploma.lilian.game.scene.handler;

import android.content.Context;
import android.util.DisplayMetrics;

import com.diploma.lilian.database.datamanager.BattleFieldDataManager;
import com.diploma.lilian.database.entity.BattleField;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.Sprite;
import com.diploma.lilian.game.OnFightListener;
import com.diploma.lilian.game.OnGateListener;
import com.diploma.lilian.game.provider.BattleFieldSpriteProvider;
import com.diploma.lilian.game.provider.ISpriteProvider;
import com.diploma.lilian.game.provider.SpriteInfo;
import com.diploma.lilian.game.scene.BattleFieldScene;
import com.diploma.lilian.game.util.BattleFieldGenerator;
import com.j256.ormlite.dao.ForeignCollection;

import java.sql.SQLException;

public class BattleFieldSceneHandler extends BaseSceneHandler<BattleFieldScene>{

    private BattleFieldDataManager battleFieldDataManager;
    private BattleField battleField;

    public BattleFieldSceneHandler(Context context, DisplayMetrics metrics, Player player) {
        super(context, metrics, player);

        scene = new BattleFieldScene(context, this.metrics.widthPixels, this.metrics.heightPixels);

        ISpriteProvider spriteProvider = new BattleFieldSpriteProvider(context, player);
        scene.setSpriteProvider(spriteProvider);

        scene.start();

        battleFieldDataManager = new BattleFieldDataManager(context);
        battleField = battleFieldDataManager.queryForAll().get(0);

    }

    public void setOnFightListener(OnFightListener onFightListener) {
        scene.setOnFightListener(onFightListener);
    }

    public int removeEnemy() {
        SpriteInfo enemy = getScene().getEnemy();

        int remainEnemy = 0;

        ForeignCollection<Sprite> enemies = battleField.getSprites();
        Sprite spriteToDelete = null;

        for(Sprite sprite : enemies){
            if(sprite.getId() == enemy.getData().getId()){
                spriteToDelete = sprite;
            }
            if(sprite.getEnemyData() != null){
                if(sprite.getId() != enemy.getData().getId()){
                    remainEnemy++;
                }
            }
        }

        spriteToDelete.setBattleField(null);
        battleField.getSprites().remove(spriteToDelete);


        battleFieldDataManager.add(battleField);

        getScene().removeEnemy();

        return remainEnemy;
    }

    public void generateBattleField() {
        int newLevel = battleField.getLevel()+1;
        try {
            battleFieldDataManager.getDao().delete(battleField);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        battleField = BattleFieldGenerator.generate(context, newLevel);

        ISpriteProvider spriteProvider = new BattleFieldSpriteProvider(context, player);
        scene.deleteAllSprite();
        scene.setSpriteProvider(spriteProvider);
        scene.start();
    }

    public void setOnGateListener(OnGateListener onGateListener) {
        scene.setOnGateListener(onGateListener);
    }
}
