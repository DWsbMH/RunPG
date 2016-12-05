package com.diploma.lilian.database.entity;

import com.diploma.lilian.game.provider.CollisionType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "sprite")
public class Sprite {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String imageName;

    @DatabaseField
    private float x;

    @DatabaseField
    private float y;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Enemy enemyData;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private BattleField battleField;

    @DatabaseField
    private int layer;

    @DatabaseField
    private CollisionType collisionType;

    public Sprite(){

    }

    public Sprite(String name, String imageName, float x, float y, BattleField battleField, int layer, CollisionType collisionType) {
        this.name = name;
        this.imageName = imageName;
        this.x = x;
        this.y = y;
        this.battleField = battleField;
        this.layer = layer;
        this.collisionType = collisionType;
    }

    public Sprite(String name, String imageName, float x, float y, Enemy enemyData, BattleField battleField, int layer, CollisionType collisionType) {
        this.name = name;
        this.imageName = imageName;
        this.x = x;
        this.y = y;
        this.enemyData = enemyData;
        this.battleField = battleField;
        this.layer = layer;
        this.collisionType = collisionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Enemy getEnemyData() {
        return enemyData;
    }

    public void setEnemyData(Enemy enemyData) {
        this.enemyData = enemyData;
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public void setBattleField(BattleField battleField) {
        this.battleField = battleField;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public CollisionType getCollisionType() {
        return collisionType;
    }

    public void setCollisionType(CollisionType collisionType) {
        this.collisionType = collisionType;
    }
}
