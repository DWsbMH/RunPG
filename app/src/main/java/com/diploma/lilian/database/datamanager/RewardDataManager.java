package com.diploma.lilian.database.datamanager;

import android.content.Context;

import com.diploma.lilian.database.entity.Reward;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class RewardDataManager extends DataManager<Reward> {
    public RewardDataManager(Context context) {
        super(context);
    }

    @Override
    public Dao<Reward, Integer> getDao() {
        try {
            return databaseHelper.getRewardsDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Reward add(Reward data) {
        try {
            dao.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Reward> queryForAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
