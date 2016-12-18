package com.diploma.lilian.runpg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.diploma.lilian.database.entity.TrackerService;
import com.diploma.lilian.game.GameActivity;
import com.diploma.lilian.mvp.StartActivity.StartActivityPresenter;
import com.diploma.lilian.mvp.StartActivity.StartActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.diploma.lilian.network.Constants.FETCH_ALL_ACTIVITY_DONE;
import static com.diploma.lilian.network.Constants.FETCH_NEW_ACTIVITY_DONE;

public class StartActivity extends BaseActivity<StartActivityView, StartActivityPresenter> implements StartActivityView, OnConnectListener {

    public static final int REQUEST_CONNECT_TRACKER = 1000;
    public static final String EXTRA_TRACKER_SERVICE = "TRACKER_SERVICE_NAME";

    @BindView(R.id.connectToList)
    ListView connectServiceList;
    private FetchReceiver receiver;
    @BindView(R.id.login)
    Button login;

    private static int updatedCount = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        receiver = new FetchReceiver();

        IntentFilter mStatusIntentFilter = new IntentFilter();
        mStatusIntentFilter.addAction(FETCH_NEW_ACTIVITY_DONE);
        mStatusIntentFilter.addAction(FETCH_ALL_ACTIVITY_DONE);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, mStatusIntentFilter);

        presenter.initList(connectServiceList);
        presenter.setOnConnectListener(this);

    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    public void onConnect(TrackerService trackerService) {
        login.setEnabled(false);
        presenter.onConnect(trackerService);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CONNECT_TRACKER) {
            onConnectionFinished(resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onConnectionFinished(int resultCode, Intent data) {
        Toast.makeText(this, "Downloading data...", Toast.LENGTH_SHORT).show();
        presenter.onConnectionFinished(resultCode, data);
    }

    @OnClick(R.id.login)
    public void play() {

        List<TrackerService> services = presenter.getConnectedTrackerServices();

        for (TrackerService trackerService : services) {
            ActivityFetcher activityFetcher = new ActivityFetcher(this, trackerService);
            activityFetcher.update();
        }

        if (services.isEmpty()) {
            Intent gameIntent = new Intent(this, GameActivity.class);
            startActivity(gameIntent);
        }

    }

    @NonNull
    @Override
    public StartActivityPresenter createPresenter() {
        return new StartActivityPresenter(this);
    }

    class FetchReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(FETCH_NEW_ACTIVITY_DONE)) {
                updatedCount++;
                if (updatedCount == presenter.getConnectedTrackerServices().size()) {
                    Intent gameIntent = new Intent(context, GameActivity.class);
                    startActivity(gameIntent);
                }
            }
            if(intent.getAction().equals(FETCH_ALL_ACTIVITY_DONE)){
                login.setEnabled(true);
                Toast.makeText(context, "Download finished!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
