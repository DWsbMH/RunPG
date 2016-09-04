package com.diploma.lilian.tracker;

import com.diploma.lilian.database.datamanager.DataManager;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.SportActivity;
import com.github.scribejava.core.oauth.OAuth20Service;

public abstract class BaseProvider implements IProvider {

    protected OAuth20Service oAuthService;
    protected DataManager<SportActivity> dataManager;
    protected Player player;

    BaseProvider(OAuth20Service oAuthService, DataManager<SportActivity> dataManager, Player player){
        this.oAuthService = oAuthService;
        this.dataManager = dataManager;
        this.player = player;
    }

    protected SportActivity convert(Object item){
        SportActivity activity = convertActivity(item);
        activity.setAssociatedPlayer(player);
        dataManager.add(activity);

        return activity;
    }

    protected abstract SportActivity convertActivity(Object item);

}
