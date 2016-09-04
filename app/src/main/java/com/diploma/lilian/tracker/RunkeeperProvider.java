package com.diploma.lilian.tracker;

import android.content.Context;

import com.diploma.lilian.database.datamanager.DataManager;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.SportActivity;
import com.diploma.lilian.database.entity.TrackerService;
import com.diploma.lilian.runkeeper.RunkeeperService;
import com.diploma.lilian.runkeeper.models.FitnessActivityFeed;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

class RunkeeperProvider extends BaseProvider {

    private static final String NAME = "Runkeeper";

    public RunkeeperProvider(OAuth20Service oAuthService, DataManager<SportActivity> dataManager, Player player) {
        super(oAuthService, dataManager, player);
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
        return NAME;
    }

    @Override
    public List<SportActivity> getAllActivityFromService(Context context, final TrackerService tracker) {

        RunkeeperService runkeeperService = new RunkeeperService(tracker.getAccess_token());
        FitnessActivityFeed fitnessActivityFeed;
        List<SportActivity> list = new ArrayList<>();
        try {
            fitnessActivityFeed = runkeeperService.fitnessActivityWrapper.getFitnessActivityFeed();
            list.addAll(convertTrackerActivities(fitnessActivityFeed.getItems()));
            do {
                if (fitnessActivityFeed.getNext() != null) {
                    fitnessActivityFeed = runkeeperService.fitnessActivityWrapper.getFitnessActivityFeed(fitnessActivityFeed.getNext());
                    list.addAll(convertTrackerActivities(fitnessActivityFeed.getItems()));
                }
            } while (fitnessActivityFeed.getNext() != null);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;

    }

    private List<SportActivity> convertTrackerActivities(FitnessActivityFeed.Item[] items) {
        List<SportActivity> temp = new ArrayList<>();
        for (FitnessActivityFeed.Item item : items) {
            temp.add(convert(item));
        }
        return temp;
    }

    @Override
    protected SportActivity convertActivity(Object item) {
        FitnessActivityFeed.Item temp_item = (FitnessActivityFeed.Item) item;

        double averageSpeed = (temp_item.getTotal_distance() / 1000) / (temp_item.getDuration() / 3600); // meters / second
        Date startDate = null;
        try {
            startDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.US).parse(temp_item.getStart_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new SportActivity(
                temp_item.getUri(),
                temp_item.getType(),
                startDate,
                temp_item.getDuration().intValue(),
                temp_item.getUtc_offset(),
                temp_item.getTotal_distance(),
                averageSpeed,
                0);
    }
}
