package com.diploma.lilian.tracker;

import android.content.Context;

import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.SportActivity;
import com.diploma.lilian.database.entity.TrackerService;
import com.diploma.lilian.database.datamanager.DataManager;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.util.List;

class StravaProvider extends BaseProvider {
    private static final String name = "Strava";

    public StravaProvider(OAuth20Service oAuthService, DataManager<SportActivity> dataManager, Player player) {
        super(oAuthService,dataManager,player);
    }

    @Override
    protected SportActivity convertActivity(Object item) {
        return null;
    }

    @Override
    public DataManager<SportActivity> getDataManager() {
        return dataManager;
    }

    @Override
    public OAuth20Service getOAuthService() {
        return oAuthService;
    }

    @Override
    public String getTrackerName() {
        return name;
    }

    @Override
    public List<SportActivity> getAllActivityFromService(Context context, TrackerService tracker) {
        return null;
    }

    @Override
    public List<SportActivity> getNewActivityFromService(Context context, TrackerService tracker) {
        return null;
    }

    @Override
    public SportActivityType getUnifiedType(String trackerType) {
        return null;
    }



    /*RunKeeper: Running
Strava: Run
RunKeeper: Hiking
Strava: Hike
RunKeeper: CrossFit
Strava: CrossFit


RunKeeper: Cycling
Strava: Ride
RunKeeper: Yoga
Strava: Yoga
RunKeeper: Snowboarding
Strava: Snowboard


RunKeeper: Walking
Strava: Walk
RunKeeper: Swimming
Strava: Swim
RunKeeper: Skating
Strava: IceSkate

*/
}
