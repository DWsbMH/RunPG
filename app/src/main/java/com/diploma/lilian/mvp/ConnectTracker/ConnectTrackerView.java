package com.diploma.lilian.mvp.ConnectTracker;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface ConnectTrackerView extends MvpView {

    void onConnectionFinished(String action, String access_token, String tracker);

}
