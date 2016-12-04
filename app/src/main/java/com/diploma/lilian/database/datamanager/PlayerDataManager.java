package com.diploma.lilian.database.datamanager;

import android.content.Context;

import com.diploma.lilian.database.entity.Attributes;
import com.diploma.lilian.database.entity.Backpack;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.PlayerSheet;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class PlayerDataManager extends DataManager<Player> {

    private static PlayerDataManager INSTANCE;
    private Player player;

    private PlayerDataManager(Context context) {
        super(context);
    }

    public static PlayerDataManager INSTANCE(Context context){

        if(INSTANCE == null){
            INSTANCE = new PlayerDataManager(context);
        }

        return INSTANCE;

    }

    @Override
    public Dao<Player, Integer> getDao() {
        try {
            dao = getDatabaseHelper().getPlayerDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dao;
    }

    public Player getPlayer(){
        if(player != null){
            return player;
        }

        try {
            List<Player> temp = dao.queryForAll();
            if(temp != null && temp.size()>0){
                player = temp.get(0);
                return player;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Player add(Player data) {

        // we only have 1 player which is created in DiplomaDBHelper.java

        return null;
/*        try {
            dao.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public List<Player> queryForAll() {
        return null;
    }

    public void update(Player player) {
        try {
            Dao<Attributes, Integer> attributesDao = databaseHelper.getAttributesDao();
            Dao<Backpack, Integer> backpacksDao = databaseHelper.getBackpackDao();
            Dao<PlayerSheet, Integer> playerSheetsDao = databaseHelper.getPlayerSheetsDao();
            playerSheetsDao.update(player.getPlayerSheet());
            backpacksDao.update(player.getBackpack());
            attributesDao.update(player.getAttributes());
            dao.update(player);

            player.setBackpack(databaseHelper.getBackpackDao().queryForAll().get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
