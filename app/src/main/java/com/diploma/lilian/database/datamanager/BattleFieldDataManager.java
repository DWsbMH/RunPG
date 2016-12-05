package com.diploma.lilian.database.datamanager;

import android.content.Context;

import com.diploma.lilian.database.entity.BattleField;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class BattleFieldDataManager extends DataManager<BattleField>{


    public BattleFieldDataManager(Context context) {
        super(context);
    }

    @Override
    public Dao<BattleField, Integer> getDao() {
        try {
            return databaseHelper.getBattleFieldDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BattleField add(BattleField data) {
        try {
            dao.createOrUpdate(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<BattleField> queryForAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
