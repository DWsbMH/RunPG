package com.diploma.lilian.runpg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.diploma.lilian.mvp.ConnectTracker.ConnectTrackerPresenter;
import com.diploma.lilian.mvp.ConnectTracker.ConnectTrackerView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConnectTracker extends MvpActivity<ConnectTrackerView, ConnectTrackerPresenter> implements ConnectTrackerView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.webView) WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_trcker);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        presenter.setup();
        presenter.initWebView(webView);

        System.out.println(presenter.getService().getAuthorizationUrl());
        webView.loadUrl(presenter.getService().getAuthorizationUrl());

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @NonNull
    @Override
    public ConnectTrackerPresenter createPresenter() {
        return new ConnectTrackerPresenter(this);
    }

    @Override
    public void onConnectionFinished(String action, String access_token, String tracker) {
        presenter.onConnectionFinished(action,access_token,tracker);
    }


}
