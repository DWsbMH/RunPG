package com.diploma.lilian.tracker;

import android.content.Context;

import com.diploma.lilian.database.entity.SportActivity;
import com.diploma.lilian.database.entity.TrackerService;
import com.diploma.lilian.database.datamanager.DataManager;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.util.List;

public interface IProvider {

    DataManager<?> getDataManager();
    OAuth20Service getOAuthService();
    String getTrackerName();
    List<SportActivity> getAllActivityFromService(Context context, TrackerService tracker);
    List<SportActivity> getNewActivityFromService(Context context, TrackerService tracker);
    SportActivityType getUnifiedType(String trackerType);


}
