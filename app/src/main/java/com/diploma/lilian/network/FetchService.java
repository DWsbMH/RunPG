package com.diploma.lilian.network;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.diploma.lilian.database.datamanager.PlayerDataManager;
import com.diploma.lilian.database.entity.SportActivity;
import com.diploma.lilian.database.entity.TrackerService;
import com.diploma.lilian.runpg.RewardDrawer;
import com.diploma.lilian.tracker.IProvider;
import com.diploma.lilian.tracker.ProviderFactory;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.util.Date;
import java.util.List;

public class FetchService extends IntentService {
    public static final String ACTION_FETCH_TOKEN = "com.diploma.lilian.network.action.FETCH_TOKEN";
    public static final String ACTION_FETCH_NEW_ACTIVITY_INGAME = "com.diploma.lilian.network.action.FETCH_NEW_ACTIVITY_INGAME";
    private static final String ACTION_FETCH_ALL_ACTIVITY = "com.diploma.lilian.network.action.FETCH_ALL_ACTIVITY";
    private static final String ACTION_FETCH_NEW_ACTIVITY = "com.diploma.lilian.network.action.FETCH_NEW_ACTIVITY";

    private static final String EXTRA_AUTH_TOKEN = "com.diploma.lilian.network.extra.AUTH_TOKEN";
    private static final String EXTRA_TRACKER = "com.diploma.lilian.network.extra.TRACKER";
    private static final String EXTRA_TRACKER_SERVICE = "com.diploma.lilian.network.extra.TRACKER_SERVICE";

    public FetchService() {
        super("FetchService");
    }

    public static void startFetchToken(Context context, String tracker, String auth_token) {

        Intent intent = new Intent(context, FetchService.class);
        intent.setAction(ACTION_FETCH_TOKEN);
        intent.putExtra(EXTRA_AUTH_TOKEN, auth_token);
        intent.putExtra(EXTRA_TRACKER, tracker);

        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            TrackerService trackerService;
            switch (action){
                case ACTION_FETCH_TOKEN:
                    final String auth_token = intent.getStringExtra(EXTRA_AUTH_TOKEN);
                    final String tracker = intent.getStringExtra(EXTRA_TRACKER);
                    handleActionFetchToken(tracker, auth_token);
                    break;
                case ACTION_FETCH_ALL_ACTIVITY:
                    trackerService = intent.getParcelableExtra(EXTRA_TRACKER_SERVICE);
                    handleActionFetchAllActivity(trackerService);
                    break;
                case ACTION_FETCH_NEW_ACTIVITY:
                    trackerService = intent.getParcelableExtra(EXTRA_TRACKER_SERVICE);
                    handleActionFetchNewActivities(trackerService);
                    break;
                case ACTION_FETCH_NEW_ACTIVITY_INGAME:
                    trackerService = intent.getParcelableExtra(EXTRA_TRACKER_SERVICE);
                    handleActionFetchNewActivitiesIngame(trackerService);
                    break;
            }
        }
    }

    private void handleActionFetchNewActivitiesIngame(TrackerService trackerService) {
        ProviderFactory factory = new ProviderFactory(getBaseContext(),trackerService.getName());
        IProvider provider = factory.create();
        List<SportActivity> list = provider.getNewActivityFromService(getBaseContext(), trackerService);

        factory.getPlayer().setLastPlayed(new Date());

        RewardDrawer rewardDrawer = new RewardDrawer(getBaseContext(), factory.getPlayer());

        if(list != null) {
            for (SportActivity sportActivity : list) {
                rewardDrawer.checkForGift(sportActivity);
            }
        }

        PlayerDataManager.INSTANCE(getBaseContext()).update(factory.getPlayer());

        Intent localIntent;
        localIntent = new Intent(Constants.FETCH_REFRESH_ACTIVITY_DONE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

    }

    private void handleActionFetchNewActivities(TrackerService trackerService) {
        ProviderFactory factory = new ProviderFactory(getBaseContext(),trackerService.getName());
        IProvider provider = factory.create();
        List<SportActivity> list = provider.getNewActivityFromService(getBaseContext(), trackerService);

        factory.getPlayer().setLastPlayed(new Date());

        RewardDrawer rewardDrawer = new RewardDrawer(getBaseContext(), factory.getPlayer());

        if(list != null) {

            for (SportActivity sportActivity : list) {
                rewardDrawer.checkForGift(sportActivity);
            }

        }
        PlayerDataManager.INSTANCE(getBaseContext()).update(factory.getPlayer());

        Intent localIntent;
        localIntent = new Intent(Constants.FETCH_NEW_ACTIVITY_DONE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

    }

    private void handleActionFetchAllActivity(TrackerService trackerService) {
        ProviderFactory factory = new ProviderFactory(getBaseContext(),trackerService.getName());
        IProvider provider = factory.create();
        provider.getAllActivityFromService(getBaseContext(), trackerService);

        factory.getPlayer().setLastPlayed(new Date());

        PlayerDataManager.INSTANCE(getBaseContext()).update(factory.getPlayer());

        System.out.println("All activity saved!");
    }

    private void handleActionFetchToken(String tracker, String auth_token) {

        Intent localIntent;

        if (auth_token== null || auth_token.isEmpty()) {
            localIntent = new Intent(Constants.FETCH_TOKEN_ERROR);
        } else {
            OAuth20Service service = new ProviderFactory(getBaseContext(),tracker).create().getOAuthService();
            OAuth2AccessToken access_token = service.getAccessToken(auth_token);

            localIntent = new Intent(Constants.FETCH_TOKEN_ACTION)
                    .putExtra(Constants.ACCESS_TOKEN_EXTRA, access_token.getAccessToken())
                    .putExtra(Constants.TRACKER_SERVICE_EXTRA, tracker);

            System.err.println(access_token);
        }

        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

    }

    public static void startFetchActivities(Context context, TrackerService tracker) {
        Intent intent = new Intent(context, FetchService.class);
        intent.setAction(ACTION_FETCH_ALL_ACTIVITY);
        intent.putExtra(EXTRA_TRACKER_SERVICE, tracker);
        context.startService(intent);
    }

    public static void startFetchNewActivities(Context context, TrackerService tracker) {
        Intent intent = new Intent(context, FetchService.class);
        intent.setAction(ACTION_FETCH_NEW_ACTIVITY);
        intent.putExtra(EXTRA_TRACKER_SERVICE, tracker);
        context.startService(intent);
    }

    public static void startFetchNewActivitiesIngame(Context context, TrackerService tracker) {
        Intent intent = new Intent(context, FetchService.class);
        intent.setAction(ACTION_FETCH_NEW_ACTIVITY_INGAME);
        intent.putExtra(EXTRA_TRACKER_SERVICE, tracker);
        context.startService(intent);
    }
}
