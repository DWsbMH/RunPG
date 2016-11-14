package com.diploma.lilian.mvp.GameActivity;

import android.support.v4.app.Fragment;

import com.diploma.lilian.game.scene.BaseScene;
import com.hannesdorfmann.mosby.mvp.MvpView;


public interface GameActivityView extends MvpView{

    void setGameScene(BaseScene gameScene, Fragment hud);
    void setFragment(Fragment fragment);
}
