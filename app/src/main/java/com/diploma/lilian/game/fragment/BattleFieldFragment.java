package com.diploma.lilian.game.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.diploma.lilian.game.view.BarView;
import com.diploma.lilian.runpg.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BattleFieldFragment extends Fragment {

    @BindView(R.id.healthBar)
    BarView playerHealthBar;
    @BindView(R.id.staminaBar)
    BarView staminaBar;

    @BindView(R.id.zoomDefault)
    Button zoom1;
    @BindView(R.id.zoom3)
    Button zoom3;
    @BindView(R.id.zoom5)
    Button zoom5;

    private static final String ARG_PLAYER_MAX_HEALTH = "ARG_PLAYER_MAX_HEALTH";
    private static final String ARG_PLAYER_ACTUAL_HEALTH = "ARG_PLAYER_ACTUAL_HEALTH";
    private static final String ARG_PLAYER_MAX_STAMINA = "ARG_PLAYER_MAX_STAMINA";
    private static final String ARG_PLAYER_ACTUAL_STAMINA = "ARG_PLAYER_ACTUAL_STAMINA";

    private int playerMaxHealth;
    private int playerActualHealth;
    private int playerMaxStamina;
    private float playerActualStamina;

    private Activity mActivity;

    private OnZoomListener onZoomListener;

    public BattleFieldFragment() {

    }

    public static BattleFieldFragment newInstance(int playerMaxHealthPoint, int playerActualHealthPoint, int maxStamina, float actualStamina) {
        BattleFieldFragment fragment = new BattleFieldFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PLAYER_MAX_HEALTH, playerMaxHealthPoint);
        args.putInt(ARG_PLAYER_ACTUAL_HEALTH, playerActualHealthPoint);
        args.putInt(ARG_PLAYER_MAX_STAMINA, maxStamina);
        args.putFloat(ARG_PLAYER_ACTUAL_STAMINA, actualStamina);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            playerMaxHealth = getArguments().getInt(ARG_PLAYER_MAX_HEALTH);
            playerActualHealth = getArguments().getInt(ARG_PLAYER_ACTUAL_HEALTH);
            playerMaxStamina = getArguments().getInt(ARG_PLAYER_MAX_STAMINA);
            playerActualStamina = getArguments().getFloat(ARG_PLAYER_ACTUAL_STAMINA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battlefield, container, false);
        ButterKnife.bind(this, view);
        playerHealthBar.setMaxPoint(playerMaxHealth);
        playerHealthBar.setActualPoint(playerActualHealth);
        staminaBar.setMaxPoint(playerMaxStamina);
        staminaBar.setActualPoint(playerActualStamina);
        return view;
    }

    public void decreaseStamina() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                staminaBar.setActualPoint(staminaBar.getActualPoint() - 0.05f);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    public interface OnZoomListener{
        void onZoom(float zoom);
    }

    @OnClick({R.id.zoomDefault, R.id.zoom3, R.id.zoom5})
    public void setZoom(View view){
        if(onZoomListener != null){
            switch (view.getId()){
                case R.id.zoomDefault:
                    onZoomListener.onZoom(1);
                    break;
                case R.id.zoom3:
                    onZoomListener.onZoom((float) 1/3);
                    break;
                case R.id.zoom5:
                    onZoomListener.onZoom((float) 1/5);
                    break;

            }
        }
    }

    public void setOnZoomListener(OnZoomListener onZoomListener) {
        this.onZoomListener = onZoomListener;
    }
}
