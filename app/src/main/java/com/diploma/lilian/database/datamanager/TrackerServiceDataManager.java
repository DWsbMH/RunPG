package com.diploma.lilian.database.datamanager;

import android.content.Context;

import com.diploma.lilian.database.entity.TrackerService;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;

import java.sql.SQLException;
import java.util.List;

public class TrackerServiceDataManager extends DataManager<TrackerService> {

    public TrackerServiceDataManager(Context context) {
        super(context);
    }

    @Override
    public Dao<TrackerService, Integer> getDao() {
        try {
            dao = getDatabaseHelper().getTrackerServiceDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dao;
    }

    public TrackerService getTrackerByName(String tracker) {
        try {
            PreparedQuery<TrackerService> preparedQuery = dao.queryBuilder().selectColumns("name").where().eq("name", tracker).prepare();
            return dao.queryForFirst(preparedQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TrackerService> getConnectibleServices() {
        try {
            PreparedQuery<TrackerService> preparedQuery = dao.queryBuilder().selectColumns("name").where().eq("connected", false).prepare();
            return dao.query(preparedQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TrackerService connectedTo(TrackerService trackerService) {
        try{
        trackerService.setConnected(true);
            dao.update(trackerService);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trackerService;
    }

    @Override
    public TrackerService add(TrackerService data) {
        try {
            dao.create(data);
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<TrackerService> queryForAll() {
        return null;
    }

    public List<TrackerService> getConnectedServices() {
        try {
            List<TrackerService> all = dao.queryForAll();
            PreparedQuery<TrackerService> preparedQuery = dao.queryBuilder().where().eq("connected", true).prepare();
            return dao.query(preparedQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

