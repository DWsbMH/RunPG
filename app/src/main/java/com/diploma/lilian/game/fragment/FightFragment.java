package com.diploma.lilian.game.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.diploma.lilian.game.OnEnemyListener;
import com.diploma.lilian.game.OnPlayerListener;
import com.diploma.lilian.runpg.R;
import com.diploma.lilian.game.view.BarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FightFragment extends Fragment implements OnPlayerListener, OnEnemyListener {
    @BindView(R.id.fight)
    Button fight;
    @BindView(R.id.enemyHealthBar)
    BarView enemyHealthBar;
    @BindView(R.id.healthBar)
    BarView playerHealthBar;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ENEMY_MAX_HEALTH = "ARG_ENEMY_MAX_HEALTH";
    private static final String ARG_PLAYER_MAX_HEALTH = "ARG_PLAYER_MAX_HEALTH";
    private static final String ARG_PLAYER_ACTUAL_HEALTH = "ARG_PLAYER_ACTUAL_HEALTH";

    // TODO: Rename and change types of parameters
    private int enemyMaxHealth;
    private int playerMaxHealth;
    private int playerActualHealth;

    private OnFightFragmentListener mListener;

    private Activity mActivity;

    public FightFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.fight)
    public void fight() {
        mListener.fight();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            enemyMaxHealth = getArguments().getInt(ARG_ENEMY_MAX_HEALTH);
            playerMaxHealth = getArguments().getInt(ARG_PLAYER_MAX_HEALTH);
            playerActualHealth = getArguments().getInt(ARG_PLAYER_ACTUAL_HEALTH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fight, container, false);
        ButterKnife.bind(this, view);
        enemyHealthBar.initPoints(enemyMaxHealth);
        playerHealthBar.setMaxPoint(playerMaxHealth);
        playerHealthBar.setActualPoint(playerActualHealth);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        if (context instanceof OnFightFragmentListener) {
            mListener = (OnFightFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFightFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public static Fragment newInstance(int enemyMaxHealth, int playerMaxHealthPoint, int playerActualHealthPoint) {
        FightFragment fragment = new FightFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ENEMY_MAX_HEALTH, enemyMaxHealth);
        args.putInt(ARG_PLAYER_MAX_HEALTH, playerMaxHealthPoint);
        args.putInt(ARG_PLAYER_ACTUAL_HEALTH, playerActualHealthPoint);
        fragment.setArguments(args);
        return fragment;
    }

    public interface OnFightFragmentListener {
        // TODO: Update argument type and name
        void fight();
    }

    @Override
    public void setPlayerHealth(final int health) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                playerHealthBar.setActualPoint(health);
            }
        });
    }

    @Override
    public void setEnemyHealth(final int health) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                enemyHealthBar.setActualPoint(health);
            }
        });
    }


}
