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

    RunkeeperProvider(OAuth20Service oAuthService, DataManager<SportActivity> dataManager, Player player) {
        super(oAuthService, dataManager, player);
    }

    @Override
    public List<SportActivity> getAllActivityFromService(Context context, final TrackerService tracker) {

        RunkeeperService runkeeperService = new RunkeeperService(tracker.getAccess_token());
        FitnessActivityFeed fitnessActivityFeed;
        List<SportActivity> list = new ArrayList<>();
        try {
            fitnessActivityFeed = runkeeperService.fitnessActivityWrapper.getFitnessActivityFeed();
            for (FitnessActivityFeed.Item item : fitnessActivityFeed.getItems()) {
                list.add(convert(item));
            }
            do {
                if (fitnessActivityFeed.getNext() != null) {
                    fitnessActivityFeed = runkeeperService.fitnessActivityWrapper.getFitnessActivityFeed(fitnessActivityFeed.getNext());
                    for (FitnessActivityFeed.Item item : fitnessActivityFeed.getItems()) {
                        list.add(convert(item));
                    }
                }
            } while (fitnessActivityFeed.getNext() != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    @Override
    public List<SportActivity> getNewActivityFromService(Context context, final TrackerService tracker) {

        RunkeeperService runkeeperService = new RunkeeperService(tracker.getAccess_token());
        FitnessActivityFeed fitnessActivityFeed;
        List<SportActivity> list = new ArrayList<>();
        try {
            fitnessActivityFeed = runkeeperService.fitnessActivityWrapper.getFitnessActivityFeed();
            for (FitnessActivityFeed.Item item : fitnessActivityFeed.getItems()) {
                SportActivity activity = convert(item);
                if (activity != null) { // új cucc, jutalmazni kell majd....
                    list.add(activity);
                } else {
                    break;
                }
            }
            do {
                if (fitnessActivityFeed.getNext() != null) {
                    fitnessActivityFeed = runkeeperService.fitnessActivityWrapper.getFitnessActivityFeed(fitnessActivityFeed.getNext());
                    for (FitnessActivityFeed.Item item : fitnessActivityFeed.getItems()) {
                        SportActivity activity = convert(item);
                        if (activity != null) { // új cucc, jutalmazni kell majd....
                            list.add(activity);
                        } else {
                            break;
                        }
                    }
                }
            } while (fitnessActivityFeed.getNext() != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    @Override
    public SportActivity convertActivity(Object item) {
        FitnessActivityFeed.Item temp_item = (FitnessActivityFeed.Item) item;

        SportActivityType type = getUnifiedType(temp_item.getType());

        double averageSpeed = (temp_item.getTotal_distance() / 1000) / (temp_item.getDuration() / 3600); // meters / second
        Date startDate = null;
        try {
            startDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.US).parse(temp_item.getStart_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new SportActivity(
                player,
                temp_item.getUri(),
                type,
                startDate,
                temp_item.getDuration().intValue(),
                temp_item.getUtc_offset(),
                temp_item.getTotal_distance(),
                averageSpeed,
                0);
    }

    @Override
    public SportActivityType getUnifiedType(String trackerType) {
        SportActivityType type = null;

        switch (trackerType) {
            case "Running":
                type = SportActivityType.RUNNING;
                break;
            case "Hiking":
                type = SportActivityType.HIKING;
                break;
            case "CrossFit":
                type = SportActivityType.CROSSFIT;
                break;
            case "Cycling":
                type = SportActivityType.CYCLING;
                break;
            case "Yoga":
                type = SportActivityType.YOGA;
                break;
            case "Snowboarding":
                type = SportActivityType.SNOWBOARDING;
                break;
            case "Walking":
                type = SportActivityType.WALKING;
                break;
            case "Swimming":
                type = SportActivityType.SWIMMING;
                break;
            case "Skating":
                type = SportActivityType.SKATING;
                break;
            default:
                type = SportActivityType.OTHER;
                break;
        }

        return type;
    }


}
