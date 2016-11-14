package com.diploma.lilian.database.datamanager;

import android.content.Context;

import com.diploma.lilian.database.entity.SportActivity;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class SportActivityDataManager extends DataManager<SportActivity> {

    public SportActivityDataManager(Context context) {
        super(context);
    }

    @Override
    public Dao<SportActivity, Integer> getDao() {
        try {
            dao = getDatabaseHelper().getSportActivityDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dao;
    }

    @Override
    public SportActivity add(SportActivity sportActivity) {
        try {

            List<SportActivity> temp = dao.queryForEq("src_id", sportActivity.getSourceId());

            if (temp != null && temp.size() == 0) {
                dao.create(sportActivity);
                return sportActivity;
            }
            if (temp != null)
                temp.clear();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<SportActivity> queryForAll() {
        return null;
    }

}
