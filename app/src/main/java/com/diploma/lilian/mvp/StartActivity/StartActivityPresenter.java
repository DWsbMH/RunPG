package com.diploma.lilian.mvp.StartActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Toast;

import com.diploma.lilian.database.datamanager.TrackerServiceDataManager;
import com.diploma.lilian.database.entity.TrackerService;
import com.diploma.lilian.network.Constants;
import com.diploma.lilian.runpg.ActivityFetcher;
import com.diploma.lilian.runpg.ConnectTracker;
import com.diploma.lilian.runpg.OnConnectListener;
import com.diploma.lilian.runpg.StartActivity;
import com.diploma.lilian.runpg.TrackerServiceAdapter;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

public class StartActivityPresenter extends MvpBasePresenter<StartActivityView> {

    private final Context context;
    private final TrackerServiceDataManager trackerServiceDataManager;

    private TrackerServiceAdapter adapter;

    public StartActivityPresenter(Context context) {
        this.context = context;
        trackerServiceDataManager = new TrackerServiceDataManager(context);
    }

    public void onConnect(TrackerService trackerService) {
        Intent intent = new Intent(context, ConnectTracker.class);
        intent.putExtra(StartActivity.EXTRA_TRACKER_SERVICE, trackerService);
        ((Activity)context).startActivityForResult(intent, StartActivity.REQUEST_CONNECT_TRACKER);
    }

    public void onConnectionFinished(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            TrackerService tracker = data.getParcelableExtra(Constants.TRACKER_SERVICE_EXTRA);

            trackerServiceDataManager.connectedTo(tracker);
            adapter.removeConnectedTrackerService(tracker);

            saveTrackerActivitiesToDatabase(tracker);

            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

        } else if (resultCode == Activity.RESULT_CANCELED) {
            // TODO show error message
        }
    }

    private void saveTrackerActivitiesToDatabase(TrackerService tracker) {
        ActivityFetcher activityFetcher = new ActivityFetcher(context, tracker);
        activityFetcher.downloadAll();
    }

    public void loadConnectibleTrackerServices() {
        adapter.loadConnectibleTrackerServices(trackerServiceDataManager.getConnectibleServices());
    }

    public void initList(ListView connectServiceList) {

        adapter = new TrackerServiceAdapter(context);

        loadConnectibleTrackerServices();

        connectServiceList.setAdapter(adapter);

    }

    public void setOnConnectListener(OnConnectListener listener) {
        adapter.setOnConnectListener(listener);
    }

    public List<TrackerService> getConnectedTrackerServices(){
        return trackerServiceDataManager.getConnectedServices();
    }
}
