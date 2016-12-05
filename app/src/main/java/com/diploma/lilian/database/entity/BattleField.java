package com.diploma.lilian.database.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "battlefield")
public class BattleField {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField
    private int level;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Sprite> sprites;

    public BattleField() {
    }

    public BattleField(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ForeignCollection<Sprite> getSprites() {
        return sprites;
    }

    public void setSprites(ForeignCollection<Sprite> sprites) {
        this.sprites = sprites;
    }
}
