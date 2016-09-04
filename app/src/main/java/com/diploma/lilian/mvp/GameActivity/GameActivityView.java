package com.diploma.lilian.mvp.GameActivity;

import com.diploma.lilian.engine.GameScene;
import com.hannesdorfmann.mosby.mvp.MvpView;


public interface GameActivityView extends MvpView{

    void setGameScene(GameScene gameScene);
    void setHealth(int health);

}
