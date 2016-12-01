package com.diploma.lilian.database.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "battlefield")
public class BattleField {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    // sprite-ok listája

    // enemy-k listája


}
