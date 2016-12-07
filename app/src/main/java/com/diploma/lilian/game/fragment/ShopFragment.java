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
import com.diploma.lilian.database.entity.PotionEffect;
import com.diploma.lilian.database.entity.PotionType;
import com.diploma.lilian.database.entity.Weapon;
import com.diploma.lilian.game.OnEquipShopListener;
import com.diploma.lilian.game.OnShopListener;
import com.diploma.lilian.game.util.Formulas;
import com.diploma.lilian.game.view.EquipmentItemRow;
import com.diploma.lilian.game.view.EquipmentItemRowAdapter;
import com.diploma.lilian.runpg.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopFragment extends Fragment {
    private static final String ARG_SHOP_TYPE = "ARG_SHOP_TYPE";

    public static final String SHOP_TYPE_WEAPON = "SHOP_TYPE_WEAPON";
    public static final String SHOP_TYPE_POTION = "SHOP_TYPE_POTION";

    @BindView(R.id.backpack_weapon_row)
    EquipmentItemRow backpackWeaponRow;
    @BindView(R.id.backpack_potion_row)
    EquipmentItemRow backpackPotionRow;
    @BindView(R.id.shop_row)
    EquipmentItemRow shopRow;
    @BindView(R.id.saler_image)
    ImageView salerImage;

    @BindView(R.id.player_gold)
    TextView playerGold;

    private String shopType;

    private OnShopListener mListener;
    private Player player;

    EquipmentItemRowAdapter<Weapon> weaponAdapter;
    EquipmentItemRowAdapter<Potion> potionAdapter;
    private EquipmentItemRowAdapter<Item> shopAdapter;

    public ShopFragment() {

    }

    public static ShopFragment newInstance(String shopType) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOP_TYPE, shopType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            shopType = getArguments().getString(ARG_SHOP_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        ButterKnife.bind(this, view);

        player = PlayerDataManager.INSTANCE(getContext()).getPlayer();

        setPlayerGold();

        weaponAdapter = new EquipmentItemRowAdapter<>(
                getContext(), new ArrayList<>(player.getBackpack().getWeapons()), new OnEquipShopListener() {
            @Override
            public void sellItem(Item item) {
                item.setBackpack(null);
                player.getPlayerSheet().setWeapon((Weapon) item);
                player.setGold(player.getGold() + 1500);
                setPlayerGold();
                try {
                    player.getBackpack().getWeapons().update((Weapon) item);
                    PlayerDataManager.INSTANCE(getContext()).update(player);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public boolean buyItem(Item item) {
                return false;
                // majd a boltos esetén...
            }
        }, "SELL"

        );

        backpackWeaponRow.setAdapter(weaponAdapter);

        potionAdapter = new EquipmentItemRowAdapter<>(
                getContext(), new ArrayList<>(player.getBackpack().getPotions()), new OnEquipShopListener() {
            @Override
            public void sellItem(Item item) {
                item.setBackpack(null);
                player.getPlayerSheet().setPotion((Potion) item);
                player.setGold(player.getGold() + 1500);
                setPlayerGold();

                try {
                    player.getBackpack().getPotions().update((Potion) item);
                    PlayerDataManager.INSTANCE(getContext()).update(player);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public boolean buyItem(Item item) {
                return false;
            }
        }, "SELL"
        );
        backpackPotionRow.setAdapter(potionAdapter);


        List<Item> items = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        if (shopType.equals(SHOP_TYPE_WEAPON)) {

            salerImage.setImageResource(R.drawable.weapon_saler);

            String[] weapons = new String[7];
            weapons[0] = "hosszukard";
            weapons[1] = "hosszutor";
            weapons[2] = "kard";
            weapons[3] = "katana";
            weapons[4] = "kes";
            weapons[5] = "szablya";
            weapons[6] = "tor";

            for (int i = 0; i < 8; i++) {

                int weapontemp = random.nextInt(weapons.length);

                int minDamage = Formulas.getRewardWeaponDamage(player.getLevel());
                int maxDamage = Formulas.getRewardWeaponDamage(player.getLevel() + 1);


                items.add(new Weapon(null, weapons[weapontemp], weapons[weapontemp], minDamage, maxDamage));
            }
        } else {
            salerImage.setImageResource(R.drawable.potion_saler);
            String[] size = new String[3];
            size[0] = "small";
            size[1] = "medium";
            size[2] = "large";

            PotionType[] type = new PotionType[4];
            type[0] = PotionType.HEALTH;
            type[1] = PotionType.STRENGTH;
            type[2] = PotionType.ENDURANCE;
            type[3] = PotionType.LUCK;

            PotionEffect effect = null;
            for (int i = 0; i < 8; i++) {
                int potionSize = random.nextInt(size.length);
                int potionType = random.nextInt(type.length);

                switch (size[potionSize]) {
                    case "small":
                        effect = PotionEffect.SMALL;
                        break;
                    case "medium":
                        effect = PotionEffect.MEDIUM;
                        break;
                    case "large":
                        effect = PotionEffect.LARGE;
                        break;
                }

                items.add(new Potion(null, size[potionSize] + "_" + type[potionType].getType(),
                        size[potionSize] + "_" + type[potionType].getType(), type[potionType], effect));
            }
            // generate potion

        }

        shopAdapter = new EquipmentItemRowAdapter<>(getContext(), items, new OnEquipShopListener() {
            @Override
            public void sellItem(Item item) {

            }

            @Override
            public boolean buyItem(Item item) {
                if (player.getGold() > 4000) {
                    if (item instanceof Weapon) {
                        item.setBackpack(player.getBackpack());
                        player.getBackpack().getWeapons().add((Weapon) item);
                        PlayerDataManager.INSTANCE(getContext()).update(player);
                        weaponAdapter.add(item);
                    } else if (item instanceof Potion) {
                        item.setBackpack(player.getBackpack());
                        player.getBackpack().getPotions().add((Potion) item);
                        PlayerDataManager.INSTANCE(getContext()).update(player);
                        potionAdapter.add(item);
                    }
                    player.setGold(player.getGold() - 4000);
                    setPlayerGold();
                    return true;
                }
                return false;
            }
        }, "BUY");

        shopRow.setAdapter(shopAdapter);


        return view;
    }

    private void setPlayerGold() {
        playerGold.setText(String.format("You have %s gold", player.getGold()));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnShopListener) {
            mListener = (OnShopListener) context;
        } else {

            throw new RuntimeException(context.toString()
                    + " must implement OnShopListener");

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.inventory_close)
    public void closeInventory() {

        weaponAdapter.closePopup();
        potionAdapter.closePopup();
        shopAdapter.closePopup();

        mListener.onShopExit();
    }


}
