package com.diploma.lilian.game.provider;

import java.util.Collection;

public interface ISpriteProvider {

    Collection<SpriteInfo> getSpriteInfoCollection();
    SpriteInfo getPlayerSpriteInfo();
    void loadSprites();

}
