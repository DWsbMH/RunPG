package com.diploma.lilian.game;

import com.diploma.lilian.game.provider.SpriteInfo;

public interface OnFightListener {

    public void fight(SpriteInfo player, SpriteInfo enemy);
    public void onFightWin();

}
