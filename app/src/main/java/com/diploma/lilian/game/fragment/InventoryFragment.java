package com.diploma.lilian.game.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diploma.lilian.database.datamanager.PlayerDataManager;
import com.diploma.lilian.database.entity.Player;
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

        PlayerDataManager playerDataManager = new PlayerDataManager(getContext());
        Player player = playerDataManager.getPlayer();
        backpackWeaponRow.setAdapter(new EquipmentItemRowAdapter<>(
                getContext(),new ArrayList<>(player.getBackpack().getWeapons())
        ));
        backpackPotionRow.setAdapter(new EquipmentItemRowAdapter<>(
                getContext(),new ArrayList<>(player.getBackpack().getPotions())
        ));

        ((TextView)playerAttributeStrength.findViewById(R.id.player_attribute_item_name)).setText("Strength");
        ((TextView)playerAttributeEndurance.findViewById(R.id.player_attribute_item_name)).setText("Endurance");
        ((TextView)playerAttributeLuck.findViewById(R.id.player_attribute_item_name)).setText("Luck");

        ((TextView)playerAttributeStrength.findViewById(R.id.player_attribute_item_number)).setText(String.valueOf(player.getAttributes().getStrength()));
        ((TextView)playerAttributeEndurance.findViewById(R.id.player_attribute_item_number)).setText(String.valueOf(player.getAttributes().getEndurance()));
        ((TextView)playerAttributeLuck.findViewById(R.id.player_attribute_item_number)).setText(String.valueOf(player.getAttributes().getLuck()));


        return view;
    }

    @OnClick(R.id.inventory_close)
    public void closeInventory(){
        mListener.onInventoryClose();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInventoryListener) {
            mListener = (OnInventoryListener) context;
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

    public interface OnInventoryListener {
        void onInventoryClose();
    }

}
