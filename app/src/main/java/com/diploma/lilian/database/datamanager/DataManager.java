package com.diploma.lilian.database.datamanager;

import android.content.Context;

import com.diploma.lilian.database.DiplomaDBHelper;
import com.j256.ormlite.dao.Dao;

public abstract class DataManager<T> implements IManager<T> {

    DiplomaDBHelper databaseHelper;
    Dao<T, Integer> dao;

    public DataManager(Context context) {
        databaseHelper = DiplomaDBHelper.INSTANCE(context);
        dao = getDao();
    }

    DiplomaDBHelper getDatabaseHelper(){
        return databaseHelper;
    }

}
