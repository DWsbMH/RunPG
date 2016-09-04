package com.diploma.lilian.database.datamanager;

import android.content.Context;
import android.util.Log;

import com.diploma.lilian.database.entity.TrackerService;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;

import java.sql.SQLException;
import java.util.LinkedList;
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

    public List<String> getConnectibleServiceNames() {

        try {
            PreparedQuery<TrackerService> preparedQuery = dao.queryBuilder().selectColumns("name").where().eq("connected", false).prepare();
            List<TrackerService> servicesToConnect = dao.query(preparedQuery);

            return getConnectToNames(servicesToConnect);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<String> getConnectToNames(List<TrackerService> temp_list) {
        List<String> names = new LinkedList<>();

        for (TrackerService service : temp_list) {
            names.add(service.getName());
            Log.e("names", service.getName());
        }
        return names;
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
//        try{
        trackerService.setConnected(true);
//            dao.update(trackerService);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

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

}

