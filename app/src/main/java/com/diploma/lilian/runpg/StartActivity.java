package com.diploma.lilian.runpg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ListView;

import com.diploma.lilian.database.entity.TrackerService;
import com.diploma.lilian.game.GameActivity;
import com.diploma.lilian.mvp.StartActivity.StartActivityPresenter;
import com.diploma.lilian.mvp.StartActivity.StartActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends BaseActivity<StartActivityView, StartActivityPresenter> implements StartActivityView, OnConnectListener {

    public static final int REQUEST_CONNECT_TRACKER = 1000;
    public static final String EXTRA_TRACKER_SERVICE = "TRACKER_SERVICE_NAME";

    @BindView(R.id.connectToList)
    ListView connectServiceList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter.initList(connectServiceList);
        presenter.setOnConnectListener(this);

    }

    @Override
    public void onConnect(TrackerService trackerService) {
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
        presenter.onConnectionFinished(resultCode, data);
    }

    @OnClick(R.id.login)
    public void play(){
        Intent gameIntent = new Intent(this, GameActivity.class);
        startActivity(gameIntent);
    }

    @NonNull
    @Override
    public StartActivityPresenter createPresenter() {
        return new StartActivityPresenter(this);
    }

}
