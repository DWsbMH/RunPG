package com.diploma.lilian.tracker;

import android.content.Context;

import com.diploma.lilian.database.datamanager.DataManager;
import com.diploma.lilian.database.datamanager.PlayerDataManager;
import com.diploma.lilian.database.datamanager.SportActivityDataManager;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.SportActivity;
import com.diploma.lilian.oauth.RunkeeperOAuth2;
import com.diploma.lilian.oauth.StravaOAuth2;
import com.diploma.lilian.runkeeper.GraphConstants;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.OAuth20Service;

public class ProviderFactory {

    private Context context;
    private String tracker;
    private Player player;

    public ProviderFactory(Context context, String tracker) {
        this.context = context;
        this.tracker = tracker;
    }

    public IProvider create(){
        IProvider temp;
        OAuth20Service service;
        DataManager<SportActivity> dataManager;
        player = PlayerDataManager.INSTANCE(context).getPlayer();

        switch (tracker){

            case "Runkeeper":
                service = new ServiceBuilder().apiKey(GraphConstants.CLIENT_ID).apiSecret(GraphConstants.CLIENT_SECRET)
                        .callback(GraphConstants.REDIRECT_URI).grantType(OAuthConstants.AUTHORIZATION_CODE).build(RunkeeperOAuth2.instance());
                dataManager = new SportActivityDataManager(context);
                temp = new RunkeeperProvider(service,dataManager,player);
                break;
            case "Strava":

                service = new ServiceBuilder().apiKey("11168").apiSecret("824e2d79adae19cf59646d62b8b4afd362739760")
                        .callback(GraphConstants.REDIRECT_URI).grantType(OAuthConstants.AUTHORIZATION_CODE).build(StravaOAuth2.instance());
                dataManager = new SportActivityDataManager(context);
                temp = new StravaProvider(service,dataManager,player);
                break;
            default:
                temp = null;
                break;
        }



        return temp;
    }

    public Player getPlayer() {
        return player;
    }
}
