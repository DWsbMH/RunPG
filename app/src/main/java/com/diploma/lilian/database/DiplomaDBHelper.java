package com.diploma.lilian.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.PlayerBuilder;
import com.diploma.lilian.database.entity.SportActivity;
import com.diploma.lilian.database.entity.TrackerService;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Date;

public class DiplomaDBHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = "DiplomaDBHelper";
    private static final String DATABASE_NAME = "diploma.db";
    private static final int DATABASE_VERSION = 4;

    private Dao<TrackerService, Integer> trackerServiceDao;
    private Dao<SportActivity, Integer> sportActivityDao;
    private Dao<Player, Integer> playerDao;

    private static DiplomaDBHelper INSTANCE;

    private DiplomaDBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public static DiplomaDBHelper INSTANCE(Context context){
        if(INSTANCE == null){
            INSTANCE = new DiplomaDBHelper(context);
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, TrackerService.class);
            TableUtils.createTable(connectionSource, SportActivity.class);
            TableUtils.createTable(connectionSource, Player.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            trackerServiceDao = getTrackerServiceDao();

            TrackerService connectTo = new TrackerService("Runkeeper");
            trackerServiceDao.create(connectTo);

            connectTo = new TrackerService("Strava");
            trackerServiceDao.create(connectTo);


            Player player = new PlayerBuilder().setName("Player 1").setLastPlayed(new Date(0)).
                    setMaxHealthPoint(100).setActualHealthPoint(100).setLevel(1).setExperienceGained(0).
                    setExperienceNeeded(300).setMaxStamina(100).setActualStamina(100).setStrength(1).
                    setSpeed(1).setEndurance(1).createPlayer();

            playerDao = getPlayerDao();
            playerDao.create(player);

            Log.d(TAG, "onCreate: player: "+player);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // onUpgrade
    }

    public Dao<TrackerService, Integer> getTrackerServiceDao() throws SQLException {
        if (trackerServiceDao == null) {
            trackerServiceDao = getDao(TrackerService.class);
        }
        return trackerServiceDao;
    }

    public Dao<SportActivity, Integer> getSportActivityDao() throws SQLException {
        if (sportActivityDao == null) {
            sportActivityDao = getDao(SportActivity.class);
        }
        return sportActivityDao;
    }

    public Dao<Player, Integer> getPlayerDao() throws SQLException {
        if (playerDao == null) {
            playerDao = getDao(Player.class);
        }
        return playerDao;
    }

    @Override
    public void close() {
        if(trackerServiceDao != null){
            trackerServiceDao.clearObjectCache();
            trackerServiceDao = null;
        }
        if(sportActivityDao != null){
            sportActivityDao.clearObjectCache();
            sportActivityDao = null;
        }
        if(playerDao != null){
            playerDao.clearObjectCache();
            playerDao = null;
        }

        super.close();
    }

}
