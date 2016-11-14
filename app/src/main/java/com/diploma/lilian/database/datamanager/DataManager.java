package com.diploma.lilian.database.datamanager;

import android.content.Context;

import com.diploma.lilian.database.DiplomaDBHelper;
import com.j256.ormlite.dao.Dao;

import java.util.List;

public abstract class DataManager<T> {

    DiplomaDBHelper databaseHelper;
    Dao<T, Integer> dao;

    public DataManager(Context context) {
        databaseHelper = DiplomaDBHelper.INSTANCE(context);
        dao = getDao();
    }

    public abstract Dao<T, Integer> getDao();

    public DiplomaDBHelper getDatabaseHelper(){
        return databaseHelper;
    }

    public abstract T add(T data);

    public abstract List<T> queryForAll();

}
