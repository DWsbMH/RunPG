package com.diploma.lilian.mvp.ConnectTracker;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.diploma.lilian.database.entity.TrackerService;
import com.diploma.lilian.database.datamanager.TrackerServiceDataManager;
import com.diploma.lilian.network.Constants;
import com.diploma.lilian.network.FetchService;
import com.diploma.lilian.runpg.StartActivity;
import com.diploma.lilian.tracker.ProviderFactory;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

public class ConnectTrackerPresenter extends MvpBasePresenter<ConnectTrackerView> {

    private Context context;
    private FetchReceiver receiver;
    private OAuth20Service service;
    private String trackerName;

    public ConnectTrackerPresenter(Context context) {
        this.context = context;
    }

    public void setup() {

        receiver = new FetchReceiver();

        IntentFilter mStatusIntentFilter = new IntentFilter();
        mStatusIntentFilter.addAction(Constants.FETCH_TOKEN_ACTION);
        mStatusIntentFilter.addAction(Constants.FETCH_TOKEN_ERROR);
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, mStatusIntentFilter);

        TrackerService trackerService = ((Activity)context).getIntent().getParcelableExtra(StartActivity.EXTRA_TRACKER_SERVICE);
        trackerName = trackerService.getName();
        service = new ProviderFactory(context,trackerName).create().getOAuthService();

    }

    public OAuth20Service getService() {
        return service;
    }

    public void onDestroy() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
    }

    public void initWebView(final WebView webView) {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(service.getConfig().getCallback())) {
                    String authCode = Uri.parse(url).getQueryParameter(OAuthConstants.CODE);
                    webView.setVisibility(View.GONE);

                    FetchService.startFetchToken(context, trackerName, authCode);

                    return true;
                }

                return super.shouldOverrideUrlLoading(view, url);
            }
        });

    }

    public void onConnectionFinished(String action, String access_token, String tracker) {

        Intent returnIntent = new Intent();
        switch (action){
            case Constants.FETCH_TOKEN_ACTION:
                ((Activity)context).setResult(Activity.RESULT_OK,returnIntent);

                TrackerServiceDataManager dm = new TrackerServiceDataManager(context);
                TrackerService service = dm.getTrackerByName(tracker);
                service.setAccess_token(access_token);

                returnIntent.putExtra(Constants.TRACKER_SERVICE_EXTRA,service);
                break;
            case Constants.FETCH_TOKEN_ERROR:
                ((Activity)context).setResult(Activity.RESULT_CANCELED,returnIntent);
                break;
        }
        ((Activity)context).finish();
    }

    class FetchReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            String access_token = intent.getStringExtra(Constants.ACCESS_TOKEN_EXTRA);
            String tracker = intent.getStringExtra(Constants.TRACKER_SERVICE_EXTRA);

            if(isViewAttached()){
                getView().onConnectionFinished(action, access_token, tracker);
            }
        }
    }
}
