package com.diploma.lilian.database.datamanager;

import android.content.Context;

import com.diploma.lilian.database.entity.Weapon;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class WeaponDataManager extends DataManager<Weapon> {


    public WeaponDataManager(Context context) {
        super(context);
    }

    @Override
    public Dao<Weapon, Integer> getDao() {
        try {
            return databaseHelper.getWeaponDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Weapon add(Weapon data) {
        try {
            getDao().create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public List<Weapon> queryForAll() {
        try {
            return getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
