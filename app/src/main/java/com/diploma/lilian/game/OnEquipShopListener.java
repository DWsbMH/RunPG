package com.diploma.lilian.game;

import com.diploma.lilian.database.entity.Item;

public interface OnEquipShopListener {

    void sellItem(Item item);
    boolean buyItem(Item item);

}
