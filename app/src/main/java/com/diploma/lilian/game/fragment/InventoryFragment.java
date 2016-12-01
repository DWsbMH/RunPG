package com.diploma.lilian.game.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diploma.lilian.database.datamanager.PlayerDataManager;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.game.view.BarView;
import com.diploma.lilian.game.view.EquipmentItemRow;
import com.diploma.lilian.game.view.EquipmentItemRowAdapter;
import com.diploma.lilian.runpg.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InventoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Player player;

    @BindView(R.id.backpack_weapon_row)
    EquipmentItemRow backpackWeaponRow;
    @BindView(R.id.backpack_potion_row)
    EquipmentItemRow backpackPotionRow;

    @BindView(R.id.player_attribute_strength)
    ViewGroup playerAttributeStrength;
    @BindView(R.id.player_attribute_endurance)
    ViewGroup playerAttributeEndurance;
    @BindView(R.id.player_attribute_luck)
    ViewGroup playerAttributeLuck;

    @BindView(R.id.player_image)
    ImageView playerImage;

    @BindView(R.id.player_attribute_free_points)
    TextView playerAttributeFreePoints;

    @BindView(R.id.player_name)
    TextView playerName;

    @BindView(R.id.player_level)
    TextView playerLevel;

    @BindView(R.id.player_info_details_healthbar)
    BarView healthBar;
    @BindView(R.id.player_info_details_experiencebar)
    BarView experienceBar;
    @BindView(R.id.player_info_details_staminabar)
    BarView staminaBar;

    private OnInventoryListener mListener;

    public InventoryFragment() {

    }

    public static InventoryFragment newInstance(String param1, String param2) {
        InventoryFragment fragment = new InventoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        ButterKnife.bind(this, view);

        player = PlayerDataManager.INSTANCE(getContext()).getPlayer();

        playerName.setText(player.getPlayerName());

        playerLevel.setText("Level: "+String.valueOf(player.getLevel()));

        playerImage.setImageResource(getResources().getIdentifier(player.getPlayerImage(), "drawable", getContext().getPackageName()));

        healthBar.setMaxPoint(player.getMaxHealthPoint());
        healthBar.setActualPoint(player.getActualHealthPoint());
        experienceBar.setMaxPoint(player.getAttributes().getExperienceNeeded());
        experienceBar.setActualPoint(player.getAttributes().getExperienceGained());
        staminaBar.setMaxPoint(player.getAttributes().getMaxStamina());
        staminaBar.setActualPoint(player.getAttributes().getActualStamina());

        playerAttributeFreePoints.setText(String.valueOf(player.getAttributes().getFreePoints()));

        backpackWeaponRow.setAdapter(new EquipmentItemRowAdapter<>(
                getContext(), new ArrayList<>(player.getBackpack().getWeapons())
        ));
        backpackPotionRow.setAdapter(new EquipmentItemRowAdapter<>(
                getContext(), new ArrayList<>(player.getBackpack().getPotions())
        ));

        ((TextView) playerAttributeStrength.findViewById(R.id.player_attribute_item_name)).setText("Strength");
        ((TextView) playerAttributeEndurance.findViewById(R.id.player_attribute_item_name)).setText("Endurance");
        ((TextView) playerAttributeLuck.findViewById(R.id.player_attribute_item_name)).setText("Luck");

        ((TextView) playerAttributeStrength.findViewById(R.id.player_attribute_item_number)).setText(String.valueOf(player.getAttributes().getStrength()));
        ((TextView) playerAttributeEndurance.findViewById(R.id.player_attribute_item_number)).setText(String.valueOf(player.getAttributes().getEndurance()));
        ((TextView) playerAttributeLuck.findViewById(R.id.player_attribute_item_number)).setText(String.valueOf(player.getAttributes().getLuck()));

        playerAttributeStrength.findViewById(R.id.player_attribute_addPoint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.getAttributes().getFreePoints() > 0){
                    player.getAttributes().setStrength(player.getAttributes().getStrength()+1);
                    player.getAttributes().setFreePoints(player.getAttributes().getFreePoints()-1);
                    playerAttributeFreePoints.setText(String.valueOf(player.getAttributes().getFreePoints()));
                }
            }
        });
        playerAttributeEndurance.findViewById(R.id.player_attribute_addPoint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.getAttributes().getFreePoints() > 0){
                    player.getAttributes().setEndurance(player.getAttributes().getEndurance()+1);
                    player.getAttributes().setFreePoints(player.getAttributes().getFreePoints()-1);
                    playerAttributeFreePoints.setText(String.valueOf(player.getAttributes().getFreePoints()));
                }
            }
        });
        playerAttributeLuck.findViewById(R.id.player_attribute_addPoint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.getAttributes().getFreePoints() > 0){
                    player.getAttributes().setLuck(player.getAttributes().getLuck()+1);
                    player.getAttributes().setFreePoints(player.getAttributes().getFreePoints()-1);
                    playerAttributeFreePoints.setText(String.valueOf(player.getAttributes().getFreePoints()));
                }
            }
        });


        return view;
    }

    @OnClick(R.id.inventory_close)
    public void closeInventory() {
        mListener.onInventoryClose();
    }

    @OnClick(R.id.reward_open)
    public void openRewardList() {
        mListener.onRewardOpen();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInventoryListener) {
            mListener = (OnInventoryListener) context;
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

    public interface OnInventoryListener {
        void onInventoryClose();
        void onRewardOpen();
    }

}
