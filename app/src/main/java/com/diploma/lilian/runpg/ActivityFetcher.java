package com.diploma.lilian.runpg;

import android.content.Context;

import com.diploma.lilian.database.entity.TrackerService;
import com.diploma.lilian.network.FetchService;

public class ActivityFetcher {
    private Context context;
    private TrackerService tracker;

    public ActivityFetcher(Context context, TrackerService tracker) {
        this.context = context;
        this.tracker = tracker;
    }

    public void downloadAll() {
        FetchService.startFetchActivities(context, tracker);
    }

    public void update(){
        FetchService.startFetchNewActivities(context, tracker);
    }

}
