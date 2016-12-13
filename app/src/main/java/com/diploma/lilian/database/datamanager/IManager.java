package com.diploma.lilian.database.datamanager;

import com.j256.ormlite.dao.Dao;

import java.util.List;

interface IManager<T> {

    public abstract Dao<T, Integer> getDao();

    public abstract T add(T data);

    public abstract List<T> queryForAll();

}
