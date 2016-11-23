package com.diploma.lilian.game;

import com.diploma.lilian.game.provider.SpriteInfo;

public interface OnFightListener {

    void fightAgainst(SpriteInfo enemy);
    void onFightWin();
    void onFightLost();
}
