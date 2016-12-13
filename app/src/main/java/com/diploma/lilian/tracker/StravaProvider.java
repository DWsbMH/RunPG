package com.diploma.lilian.tracker;

import android.content.Context;

import com.diploma.lilian.database.datamanager.DataManager;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.SportActivity;
import com.diploma.lilian.database.entity.TrackerService;
import com.diploma.lilian.strava.connector.JStrava;
import com.diploma.lilian.strava.connector.JStravaV3;
import com.diploma.lilian.strava.entities.activity.Activity;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

class StravaProvider extends BaseProvider {

    StravaProvider(OAuth20Service oAuthService, DataManager<SportActivity> dataManager, Player player) {
        super(oAuthService,dataManager,player);
    }

    @Override
    public List<SportActivity> getAllActivityFromService(Context context, TrackerService tracker) {

        JStrava jStrava = new JStravaV3(tracker.getAccess_token());
        List<Activity> currentAthleteActivitiesAll = jStrava.getCurrentAthleteActivitiesAll();

        List<SportActivity> list = new ArrayList<>();

        for(Activity activity : currentAthleteActivitiesAll){

            list.add(convert(activity));

        }

        return list;
    }

    @Override
    public List<SportActivity> getNewActivityFromService(Context context, TrackerService tracker) {
        JStrava jStrava = new JStravaV3(tracker.getAccess_token());

        int resultsPerPage = 30;
        int page = 1;

        List<SportActivity> list = new ArrayList<>();
        List<Activity> activitiesPerPage;

        while ((activitiesPerPage = jStrava.getCurrentAthleteActivities(page, resultsPerPage)).size() > 0) {
            for(Activity item : activitiesPerPage){

                SportActivity activity = convert(item);
                if (activity != null) { // új cucc, jutalmazni kell majd....
                    list.add(activity);
                } else {
                    break;
                }

            }

            page++;
        }






        return list;
    }

    @Override
    public SportActivity convertActivity(Object item) {
        Activity activity = (Activity) item;

        Date startDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            startDate = sdf.parse(activity.getStart_date());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new SportActivity(player,String.valueOf(activity.getId()),getUnifiedType(activity.getType()),
                startDate,activity.getElapsed_time(),0,activity.getDistance(),activity.getAverage_speed(),activity.getMax_speed());
    }

    @Override
    public SportActivityType getUnifiedType(String trackerType) {
        SportActivityType type;

        switch (trackerType) {
            case "Run":
                type = SportActivityType.RUNNING;
                break;
            case "Hike":
                type = SportActivityType.HIKING;
                break;
            case "CrossFit":
                type = SportActivityType.CROSSFIT;
                break;
            case "Ride":
                type = SportActivityType.CYCLING;
                break;
            case "Yoga":
                type = SportActivityType.YOGA;
                break;
            case "Snowboard":
                type = SportActivityType.SNOWBOARDING;
                break;
            case "Walk":
                type = SportActivityType.WALKING;
                break;
            case "Swim":
                type = SportActivityType.SWIMMING;
                break;
            case "IceSkate":
                type = SportActivityType.SKATING;
                break;
            default:
                type = SportActivityType.OTHER;
                break;
        }

        return type;
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
