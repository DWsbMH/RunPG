package com.diploma.lilian.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.diploma.lilian.database.entity.Attributes;
import com.diploma.lilian.database.entity.Backpack;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.PlayerBuilder;
import com.diploma.lilian.database.entity.Potion;
import com.diploma.lilian.database.entity.Reward;
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

    private Dao<TrackerService, Integer> trackerServiceDao;
    private Dao<SportActivity, Integer> sportActivityDao;
    private Dao<Player, Integer> playerDao;
    private Dao<Potion, Integer> potionDao;
    private Dao<Weapon, Integer> weaponDao;
    private Dao<Attributes, Integer> attributesDao;
    private Dao<Backpack, Integer> backpackDao;
    private Dao<Reward, Integer> rewardsDao;

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

            weaponDao.create(new Weapon(backpack, "kard", "kard", 5, 10));
            weaponDao.create(new Weapon(backpack, "kard", "kard", 5, 10));
            weaponDao.create(new Weapon(backpack, "kard", "kard", 5, 10));

/*
            potionDao.create(new Potion(backpack, "small health", "small_health", 2, PotionType.HEALTH));
            potionDao.create(new Potion(backpack, "small luck", "small_luck", 2, PotionType.LUCK));
            potionDao.create(new Potion(backpack, "small strength", "small_strength", 2, PotionType.STRENGTH));
            potionDao.create(new Potion(backpack, "small endurance", "small_endurance", 2, PotionType.ENDURANCE));

            potionDao.create(new Potion(backpack, "medium health", "medium_health", 4, PotionType.HEALTH));
            potionDao.create(new Potion(backpack, "medium luck", "medium_luck", 4, PotionType.LUCK));
            potionDao.create(new Potion(backpack, "medium strength", "medium_strength", 4, PotionType.STRENGTH));
            potionDao.create(new Potion(backpack, "medium endurance", "medium_endurance", 4, PotionType.ENDURANCE));

            potionDao.create(new Potion(backpack, "large health", "large_health", 6, PotionType.HEALTH));
            potionDao.create(new Potion(backpack, "large luck", "large_luck", 6, PotionType.LUCK));
            potionDao.create(new Potion(backpack, "large strength", "large_strength ", 6, PotionType.STRENGTH));
            potionDao.create(new Potion(backpack, "large endurance", "large_endurance", 6, PotionType.ENDURANCE));
*/

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

            Player player = new PlayerBuilder().setPlayerName("Player 1").setPlayerImage("player1").setLastPlayed(new Date(0)).
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

    public Dao<Backpack, Integer> getBackpackDao() throws SQLException {
        if (backpackDao == null) {
            backpackDao = getDao(Backpack.class);
        }
        return backpackDao;
    }

    public Dao<Reward, Integer> getRewardsDao() throws SQLException {
        if (rewardsDao == null) {
            rewardsDao = getDao(Reward.class);
        }
        return rewardsDao;
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
