package com.diploma.lilian.game;

import com.diploma.lilian.game.entity.Enemy;
import com.diploma.lilian.game.entity.Player;

public interface OnFightListener {

    public void fight(Player player, Enemy enemy);
    public void onFightWin();

}
