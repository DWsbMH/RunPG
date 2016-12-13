package com.diploma.lilian.tracker;

import com.diploma.lilian.database.datamanager.DataManager;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.SportActivity;
import com.github.scribejava.core.oauth.OAuth20Service;

abstract class BaseProvider implements IProvider {

    private OAuth20Service oAuthService;
    private DataManager<SportActivity> dataManager;
    protected Player player;

    BaseProvider(OAuth20Service oAuthService, DataManager<SportActivity> dataManager, Player player){
        this.oAuthService = oAuthService;
        this.dataManager = dataManager;
        this.player = player;
    }

    SportActivity convert(Object item){
        SportActivity activity = convertActivity(item);
        activity.setAssociatedPlayer(player);
        return dataManager.add(activity);
    }

    public OAuth20Service getOAuthService() {
        return oAuthService;
    }
}
