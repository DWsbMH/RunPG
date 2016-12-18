package com.diploma.lilian.database.datamanager;

import com.j256.ormlite.dao.Dao;

import java.util.List;

interface IManager<T> {

    Dao<T, Integer> getDao();

    T add(T data);

    List<T> queryForAll();

}
