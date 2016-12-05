package com.diploma.lilian.database.datamanager;

import android.content.Context;

import com.diploma.lilian.database.entity.Sprite;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class SpriteDataManager extends DataManager<Sprite>{


    public SpriteDataManager(Context context) {
        super(context);
    }

    @Override
    public Dao<Sprite, Integer> getDao() {
        try {
            return databaseHelper.getSpriteDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Sprite add(Sprite data) {
        try {
            dao.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Sprite> queryForAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
