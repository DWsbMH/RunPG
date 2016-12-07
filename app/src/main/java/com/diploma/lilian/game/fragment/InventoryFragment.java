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
import com.diploma.lilian.database.entity.Item;
import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.database.entity.Potion;
import com.diploma.lilian.database.entity.Weapon;
import com.diploma.lilian.game.OnEquipInventoryListener;
import com.diploma.lilian.game.util.Formulas;
import com.diploma.lilian.game.view.BarView;
import com.diploma.lilian.game.view.EquipmentItemRow;
import com.diploma.lilian.game.view.EquipmentItemRowAdapter;
import com.diploma.lilian.runpg.R;

import java.sql.SQLException;
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
    @BindView(R.id.equippedWeapon)
    ImageView equippedWeapon;
    @BindView(R.id.equippedStrength)
    ImageView equippedStrength;
    @BindView(R.id.equippedEndurance)
    ImageView equippedEndurance;
    @BindView(R.id.equippedLuck)
    ImageView equippedLuck;

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

    private EquipmentItemRowAdapter<Weapon> weaponAdapter;
    private EquipmentItemRowAdapter<Potion> potionAdapter;

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

        playerLevel.setText("Level: " + String.valueOf(player.getLevel()));

        playerImage.setImageResource(getResources().getIdentifier(player.getPlayerImage(), "drawable", getContext().getPackageName()));

        healthBar.setMaxPoint(player.getMaxHealthPoint());
        healthBar.setActualPoint(player.getActualHealthPoint());
        experienceBar.setMaxPoint(player.getAttributes().getExperienceNeeded());
        experienceBar.setActualPoint(player.getAttributes().getExperienceGained());
        staminaBar.setMaxPoint(player.getAttributes().getMaxStamina());
        staminaBar.setActualPoint(player.getAttributes().getActualStamina());

        playerAttributeFreePoints.setText(String.valueOf(player.getAttributes().getFreePoints()));

       weaponAdapter = new EquipmentItemRowAdapter<>(
                getContext(), new ArrayList<>(player.getBackpack().getWeapons()), new OnEquipInventoryListener() {
            @Override
            public Weapon equipItem(Item item) {
                Weapon returnWeapon = null;
                item.setBackpack(null);
                try {
                    player.getBackpack().getWeapons().update((Weapon) item);
                    PlayerDataManager.INSTANCE(getContext()).update(player);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(player.getPlayerSheet().getWeapon() != null){
                    player.getPlayerSheet().getWeapon().setBackpack(player.getBackpack());
                    try {
                        player.getBackpack().getWeapons().update(player.getPlayerSheet().getWeapon());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    returnWeapon = player.getPlayerSheet().getWeapon();
                }
                player.getPlayerSheet().setWeapon((Weapon) item);
                setEquippedImage(equippedWeapon, item.getImageName());
                return returnWeapon;
            }
        }
        );

        backpackWeaponRow.setAdapter(weaponAdapter);

        potionAdapter = new EquipmentItemRowAdapter<>(
                getContext(), new ArrayList<>(player.getBackpack().getPotions()), new OnEquipInventoryListener() {
            @Override
            public Potion equipItem(Item item) {
                item.setBackpack(null);
                player.getPlayerSheet().setPotion((Potion) item);
                try {
                    player.getBackpack().getPotions().update((Potion) item);
                    PlayerDataManager.INSTANCE(getContext()).update(player);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                switch (((Potion) item).getPotionType()){
                    case HEALTH:
                        if(player.getActualHealthPoint()*((Potion) item).getEffect().getEffect() > player.getMaxHealthPoint()){
                            player.setActualHealthPoint(player.getMaxHealthPoint());
                        } else {
                            player.setActualHealthPoint((int) (player.getActualHealthPoint()*((Potion) item).getEffect().getEffect()));
                        }
                        healthBar.setActualPoint(player.getActualHealthPoint());

                        break;
                    case LUCK:
                        setEquippedImage(equippedLuck, item.getImageName());
                        break;
                    case STRENGTH:
                        setEquippedImage(equippedStrength, item.getImageName());
                        break;
                    case ENDURANCE:
                        healthBar.setActualPoint(player.getActualHealthPoint());
                        healthBar.setMaxPoint(player.getMaxHealthPoint());
                        setEquippedImage(equippedEndurance, item.getImageName());
                        break;
                }
                return null;
            }
        }
        );

        backpackPotionRow.setAdapter(potionAdapter);

        ((TextView) playerAttributeStrength.findViewById(R.id.player_attribute_item_name)).setText("Strength");
        ((TextView) playerAttributeEndurance.findViewById(R.id.player_attribute_item_name)).setText("Endurance");
        ((TextView) playerAttributeLuck.findViewById(R.id.player_attribute_item_name)).setText("Luck");

        ((TextView) playerAttributeStrength.findViewById(R.id.player_attribute_item_number)).setText(String.valueOf(player.getAttributes().getStrength()));
        ((TextView) playerAttributeEndurance.findViewById(R.id.player_attribute_item_number)).setText(String.valueOf(player.getAttributes().getEndurance()));
        ((TextView) playerAttributeLuck.findViewById(R.id.player_attribute_item_number)).setText(String.valueOf(player.getAttributes().getLuck()));

        playerAttributeStrength.findViewById(R.id.player_attribute_addPoint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.getAttributes().getFreePoints() > 0) {
                    player.getAttributes().setStrength(player.getAttributes().getStrength() + 1);
                    player.getAttributes().setFreePoints(player.getAttributes().getFreePoints() - 1);
                    playerAttributeFreePoints.setText(String.valueOf(player.getAttributes().getFreePoints()));
                    ((TextView) playerAttributeStrength.findViewById(R.id.player_attribute_item_number)).setText(String.valueOf(player.getAttributes().getStrength()));
                    healthBar.setMaxPoint(player.getMaxHealthPoint());
                }
            }
        });
        playerAttributeEndurance.findViewById(R.id.player_attribute_addPoint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.getAttributes().getFreePoints() > 0) {
                    player.getAttributes().setEndurance(player.getAttributes().getEndurance() + 1);
                    player.getAttributes().setFreePoints(player.getAttributes().getFreePoints() - 1);
                    player.getAttributes().setMaxHealthPoint(Formulas.getMaxHealth(player));
                    playerAttributeFreePoints.setText(String.valueOf(player.getAttributes().getFreePoints()));
                    ((TextView) playerAttributeEndurance.findViewById(R.id.player_attribute_item_number)).setText(String.valueOf(player.getAttributes().getEndurance()));
                    healthBar.setMaxPoint(player.getMaxHealthPoint()    );
                }
            }
        });
        playerAttributeLuck.findViewById(R.id.player_attribute_addPoint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.getAttributes().getFreePoints() > 0) {
                    player.getAttributes().setLuck(player.getAttributes().getLuck() + 1);
                    player.getAttributes().setFreePoints(player.getAttributes().getFreePoints() - 1);
                    playerAttributeFreePoints.setText(String.valueOf(player.getAttributes().getFreePoints()));
                    ((TextView) playerAttributeLuck.findViewById(R.id.player_attribute_item_number)).setText(String.valueOf(player.getAttributes().getLuck()));
                }
            }
        });

        if(player.getPlayerSheet().getWeapon() != null){
            setEquippedImage(equippedWeapon, player.getPlayerSheet().getWeapon().getImageName());
        }
        if(player.getPlayerSheet().getEndurance() != null){
            setEquippedImage(equippedEndurance, player.getPlayerSheet().getEndurance().getImageName());
        }
        if(player.getPlayerSheet().getStrength() != null){
            setEquippedImage(equippedStrength, player.getPlayerSheet().getStrength().getImageName());
        }
        if(player.getPlayerSheet().getLuck() != null){
            setEquippedImage(equippedLuck, player.getPlayerSheet().getLuck().getImageName());
        }


        return view;
    }

    private void setEquippedImage(ImageView iv, String imageName) {
        iv.setImageResource(getResources().getIdentifier(imageName,"drawable",getContext().getPackageName()));
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

        weaponAdapter.closePopup();
        potionAdapter.closePopup();

        mListener = null;
    }

    public interface OnInventoryListener {
        void onInventoryClose();

        void onRewardOpen();
    }

}
