package com.diploma.lilian.database.datamanager;

import android.content.Context;

import com.diploma.lilian.database.entity.Enemy;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class EnemyDataManager extends DataManager<Enemy>{


    public EnemyDataManager(Context context) {
        super(context);
    }

    @Override
    public Dao<Enemy, Integer> getDao() {
        try {
            return databaseHelper.getEnemyDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Enemy add(Enemy data) {
        try {
            dao.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Enemy> queryForAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
