package com.diploma.lilian.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.diploma.lilian.database.entity.Attributes;
import com.diploma.lilian.database.entity.Backpack;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.PlayerBuilder;
import com.diploma.lilian.database.entity.Potion;
import com.diploma.lilian.database.entity.PotionType;
import com.diploma.lilian.database.entity.SportActivity;
import com.diploma.lilian.database.entity.TrackerService;
import com.diploma.lilian.database.entity.Weapon;
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
    private final Context context;

    private Dao<TrackerService, Integer> trackerServiceDao;
    private Dao<SportActivity, Integer> sportActivityDao;
    private Dao<Player, Integer> playerDao;
    private Dao<Potion, Integer> potionDao;
    private Dao<Weapon, Integer> weaponDao;
    private Dao<Attributes, Integer> attributesDao;

    private static DiplomaDBHelper INSTANCE;

    private DiplomaDBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
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
            TableUtils.createTable(connectionSource, Backpack.class);
            TableUtils.createTable(connectionSource, Weapon.class);
            TableUtils.createTable(connectionSource, Potion.class);
            TableUtils.createTable(connectionSource, Attributes.class);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            trackerServiceDao = getTrackerServiceDao();

            TrackerService connectTo = new TrackerService("Runkeeper");
            trackerServiceDao.create(connectTo);

            connectTo = new TrackerService("Strava");
            trackerServiceDao.create(connectTo);

            Dao<Backpack, Integer> backpackDao = getDao(Backpack.class);
            Dao<Weapon, Integer> weaponDao = getDao(Weapon.class);
            Dao<Potion, Integer> potionDao = getDao(Potion.class);
            Dao<Attributes, Integer> attributesDao = getDao(Attributes.class);

            Backpack backpack = new Backpack();
            backpackDao.create(backpack);

            Weapon weapon = new Weapon(backpack, "kard", "tile0001", 5, 10);
            Potion potion = new Potion(backpack, "health", "tile001.png", 5, PotionType.HEALTH);
            weaponDao.create(weapon);
            weaponDao.create(weapon);
            weaponDao.create(weapon);
            weaponDao.create(weapon);
            weaponDao.create(weapon);
            weaponDao.create(weapon);
            weaponDao.create(weapon);
            weaponDao.create(weapon);
            potionDao.create(potion);

            Attributes attributes = new Attributes();
            attributes.setLevel(1);
            attributes.setMaxHealthPoint(100);
            attributes.setActualHealthPoint(100);
            attributes.setMaxStamina(100);
            attributes.setActualStamina(100);
            attributes.setExperienceGained(0);
            attributes.setExperienceNeeded(100);
            attributes.setStrength(1);
            attributes.setLuck(1);
            attributes.setEndurance(1);

            attributesDao.create(attributes);

            Player player = new PlayerBuilder().setPlayerName("Player 1").setPlayerImage("player_image").setLastPlayed(new Date(0)).
                    setBackpack(backpack).setAttributes(attributes).createPlayer();

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

    public Dao<Potion, Integer> getPotionDao() throws SQLException {
        if (potionDao == null) {
            potionDao = getDao(Potion.class);
        }
        return potionDao;
    }

    public Dao<Weapon, Integer> getWeaponDao() throws SQLException {
        if (weaponDao == null) {
            weaponDao = getDao(Weapon.class);
        }
        return weaponDao;
    }

    public Dao<Attributes, Integer> getAttributesDao() throws SQLException {
        if (attributesDao == null) {
            attributesDao = getDao(Attributes.class);
        }
        return attributesDao;
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
