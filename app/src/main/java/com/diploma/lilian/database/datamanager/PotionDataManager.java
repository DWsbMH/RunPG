package com.diploma.lilian.database.datamanager;

import android.content.Context;

import com.diploma.lilian.database.entity.Potion;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class PotionDataManager extends DataManager<Potion> {
    public PotionDataManager(Context context) {
        super(context);
    }

    @Override
    public Dao<Potion, Integer> getDao() {
        try {
            dao = getDatabaseHelper().getPotionDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dao;
    }

    @Override
    public Potion add(Potion data) {
        try {
            getDao().create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Potion> queryForAll() {
        try {
            return getDatabaseHelper().getPotionDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
