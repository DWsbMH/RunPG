package com.diploma.lilian.database.datamanager;

import android.content.Context;

import com.diploma.lilian.database.entity.Attributes;
import com.diploma.lilian.database.entity.Backpack;
import com.diploma.lilian.database.entity.Player;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class PlayerDataManager extends DataManager<Player> {

    public PlayerDataManager(Context context) {
        super(context);
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
        try {
            List<Player> temp = dao.queryForAll();
            if(temp != null && temp.size()>0){
                return temp.get(0);
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
            backpacksDao.update(player.getBackpack());
            attributesDao.update(player.getAttributes());
            dao.update(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
