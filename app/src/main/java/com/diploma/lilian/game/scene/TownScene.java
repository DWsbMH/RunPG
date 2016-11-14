package com.diploma.lilian.game.scene;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.diploma.lilian.engine.FogOfWar;
import com.diploma.lilian.engine.Viewport;
import com.diploma.lilian.engine.ai.IPathFinder;
import com.diploma.lilian.engine.collition.CollisionDetector;

public class TownScene extends BaseScene {
    public TownScene(Context context, int surfaceWidth, int surfaceHeight) {
        super(context, surfaceWidth, surfaceHeight);
    }

    @Override
    public Fragment getHUD() {
        return new Fragment();
    }

    @Override
    protected Viewport createViewport() {
        return null;
    }

    @Override
    protected CollisionDetector createCollisionDetector() {
        return null;
    }

    @Override
    protected IPathFinder createPathFinder() {
        return null;
    }

    @Override
    protected FogOfWar createFogOfWar() {
        return null;
    }

    @Override
    public void init(Context context) {

    }

    @Override
    public void update(long elapsedTime) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
