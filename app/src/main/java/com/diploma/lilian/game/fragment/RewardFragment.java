package com.diploma.lilian.game.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.diploma.lilian.database.datamanager.RewardDataManager;
import com.diploma.lilian.database.datamanager.TrackerServiceDataManager;
import com.diploma.lilian.database.entity.Reward;
import com.diploma.lilian.database.entity.TrackerService;
import com.diploma.lilian.network.Constants;
import com.diploma.lilian.runpg.ActivityFetcher;
import com.diploma.lilian.runpg.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RewardFragment extends Fragment {

    private OnRewardListener mListener;
    private List<Reward> rewards;
    private FetchReceiver fetchReceiver;
    private IntentFilter filter;
    private RewardDataManager rewardDataManager;

    private RewardListAdapter adapter;
    @BindView(R.id.reward_list)
    ListView rewardList;

    public RewardFragment() {
        rewardDataManager = new RewardDataManager(getContext());
        rewards = rewardDataManager.queryForAll();
        filter = new IntentFilter(Constants.FETCH_REFRESH_ACTIVITY_DONE);
        fetchReceiver = new FetchReceiver();
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(fetchReceiver, filter);
    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(fetchReceiver);
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reward, container, false);
        ButterKnife.bind(this,view);

        adapter = new RewardListAdapter(getContext(), rewards);
        rewardList.setAdapter(adapter);


        return view;
    }

    @OnClick(R.id.reward_close)
    public void closeRewardList(){
        mListener.onRewardListClose();
    }

    @OnClick(R.id.reward_refresh)
    public void refreshReward(){
        List<TrackerService> services = new TrackerServiceDataManager(getContext()).getConnectedServices();

        for (TrackerService trackerService : services) {
            ActivityFetcher activityFetcher = new ActivityFetcher(getContext(), trackerService);
            activityFetcher.refreshInGame();
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRewardListener) {
            mListener = (OnRewardListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnRewardListener {
        void onRewardListClose();
    }

    class FetchReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            rewards = rewardDataManager.queryForAll();
            adapter.setData(rewards);
        }

    }


}
