package com.diploma.lilian.game.util;

import android.content.Context;

import com.diploma.lilian.database.datamanager.BattleFieldDataManager;
import com.diploma.lilian.database.datamanager.EnemyDataManager;
import com.diploma.lilian.database.datamanager.SpriteDataManager;
import com.diploma.lilian.database.entity.BattleField;
import com.diploma.lilian.database.entity.Enemy;
import com.diploma.lilian.database.entity.Sprite;
import com.diploma.lilian.game.provider.CollisionType;
import com.diploma.lilian.game.scene.BattleFieldScene;
import com.diploma.lilian.runpg.R;

import java.util.List;
import java.util.Random;

public class BattleFieldGenerator {

    public static BattleField generate(Context context, int level){
        SpriteDataManager spriteDataManager = new SpriteDataManager(context);
        EnemyDataManager enemyDataManager = new EnemyDataManager(context);
        BattleFieldDataManager battleFieldDataManager = new BattleFieldDataManager(context);

        String[] enemies = context.getResources().getStringArray(R.array.enemies);
        Random random = new Random(System.currentTimeMillis());

        BattleField battleField = new BattleField(level);
        battleFieldDataManager.add(battleField);

        Sprite sprite;
        Enemy enemy;

        sprite = new Sprite("grass", "grass", 0, 0, battleField, BattleFieldScene.GROUND_LAYER, CollisionType.INVALID);
        spriteDataManager.add(sprite);

        sprite = new Sprite("gates", "gates", BattleFieldScene.WORLD_WIDTH / 2, BattleFieldScene.WORLD_HEIGHT / 2, battleField, BattleFieldScene.MAIN_LAYER, CollisionType.PLAYER_BUILDING);
        spriteDataManager.add(sprite);

        for(int i = 0; i<20;i++){
            enemy = new Enemy(level);
            enemyDataManager.add(enemy);
            sprite = new Sprite(enemies[random.nextInt(enemies.length)], enemies[random.nextInt(enemies.length)], random.nextInt(BattleFieldScene.WORLD_WIDTH), random.nextInt(BattleFieldScene.WORLD_HEIGHT),enemy,battleField, BattleFieldScene.MAIN_LAYER, CollisionType.PLAYER_ENEMY);
            spriteDataManager.add(sprite);
        }

        battleFieldDataManager.add(battleField);

        List<BattleField> temp = battleFieldDataManager.queryForAll();

        return temp.get(0);
    }


}
