package com.diploma.lilian.game.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diploma.lilian.runpg.R;
import com.diploma.lilian.game.view.BarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BattleFieldFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BattleFieldFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BattleFieldFragment extends Fragment {

    @BindView(R.id.healthBar)
    BarView playerHealthBar;
    @BindView(R.id.staminaBar)
    BarView staminaBar;

    private static final String ARG_PLAYER_MAX_HEALTH = "ARG_PLAYER_MAX_HEALTH";
    private static final String ARG_PLAYER_ACTUAL_HEALTH = "ARG_PLAYER_ACTUAL_HEALTH";

    private int playerMaxHealth;
    private int playerActualHealth;

    private OnFragmentInteractionListener mListener;

    public BattleFieldFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BattleFieldFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BattleFieldFragment newInstance(int playerMaxHealthPoint, int playerActualHealthPoint) {
        BattleFieldFragment fragment = new BattleFieldFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PLAYER_MAX_HEALTH, playerMaxHealthPoint);
        args.putInt(ARG_PLAYER_ACTUAL_HEALTH, playerActualHealthPoint);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            playerMaxHealth = getArguments().getInt(ARG_PLAYER_MAX_HEALTH);
            playerActualHealth = getArguments().getInt(ARG_PLAYER_ACTUAL_HEALTH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default, container, false);
        ButterKnife.bind(this, view);
        playerHealthBar.setMaxPoint(playerMaxHealth);
        playerHealthBar.setActualPoint(playerActualHealth);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFightFragmentListener) {
//            mListener = (OnFightFragmentListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFightFragmentListener");
//        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
