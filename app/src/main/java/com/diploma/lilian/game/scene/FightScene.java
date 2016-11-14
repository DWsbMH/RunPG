package com.diploma.lilian.game.scene;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.diploma.lilian.engine.FogOfWar;
import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.engine.Map;
import com.diploma.lilian.engine.Vec3;
import com.diploma.lilian.engine.Viewport;
import com.diploma.lilian.engine.ai.IPathFinder;
import com.diploma.lilian.engine.ai.IsoGridPathFinder;
import com.diploma.lilian.engine.collition.CollisionDetector;
import com.diploma.lilian.engine.collition.OnCollisionListener;
import com.diploma.lilian.game.CollisionType;
import com.diploma.lilian.game.fragment.FightFragment;
import com.diploma.lilian.game.provider.BaseSpriteProvider;
import com.diploma.lilian.game.provider.SpriteInfo;
import com.diploma.lilian.game.scene.handler.FightSceneHandler;

import java.util.ArrayList;
import java.util.Collection;

import javax.microedition.khronos.opengles.GL11;

public class FightScene extends BaseScene implements OnCollisionListener {

    private static final String TAG = "FightScene";
    private static int MAIN_LAYER;
    private static int GROUND_LAYER;
    private SpriteInfo player;
    private SpriteInfo enemy;
    private FightSceneHandler onFightTurnListener;


    public FightScene(Context context, int surfaceWidth, int surfaceHeight, BaseSpriteProvider spriteProvider) {
        super(context, surfaceWidth, surfaceHeight);
        this.spriteProvider = spriteProvider;
    }

    @Override
    protected Viewport createViewport() {
        Viewport vp = new Viewport(0, 0, surfaceWidth, surfaceHeight, surfaceWidth, surfaceHeight, 0.5f);

        GROUND_LAYER = vp.addLayer(new Map(1));
        MAIN_LAYER = vp.addLayer(new Map(50));

        return vp;
    }

    @Override
    protected CollisionDetector createCollisionDetector() {
        Vec3 mapCorner1 = new Vec3(0, 0, 0);
        Vec3 mapCorner2 = new Vec3(surfaceWidth, surfaceHeight, 400);

        CollisionDetector collisionDetector = new CollisionDetector(mapCorner1, mapCorner2, new Vec3(40, 40, 40));
        collisionDetector.addOnCollisionListener(this);

        return collisionDetector;
    }

    @Override
    protected IPathFinder createPathFinder() {
        return new IsoGridPathFinder(surfaceWidth, surfaceHeight, 50);
    }

    @Override
    protected FogOfWar createFogOfWar() {
        return null;
    }

    @Override
    public void init(Context context) {
        Collection<SpriteInfo> spriteInfos = spriteProvider.getSpriteInfoCollection();
        for (SpriteInfo spriteInfo : spriteInfos) {

            if (spriteInfo.getSprite().getCollisionTypeBitmap() != CollisionType.INVALID.getValue()) {
                collitionDetector.add(spriteInfo.getSprite());
            }

            vp.addElement(spriteInfo.getSprite(), spriteInfo.getLayerType());

        }


        enemy = new ArrayList<>(spriteProvider.getEnemiesSpriteInfo()).get(0);

        if (enemy.getSprite() != null && enemy.getSprite().getCollisionTypeBitmap() != CollisionType.INVALID.getValue()) {
            collitionDetector.add(enemy.getSprite());
        }

        vp.addElement(enemy.getSprite(), enemy.getLayerType());

        player = spriteProvider.getPlayerSpriteInfo();
        if (player.getSprite() != null && player.getSprite().getCollisionTypeBitmap() != CollisionType.INVALID.getValue()) {
            collitionDetector.add(player.getSprite());
        }

        vp.addElement(player.getSprite(), player.getLayerType());

    }

    @Override
    public void update(long l) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void handleCollision(IsoSprite s1, IsoSprite s2, int collisionGroupMask) {
        Log.d(TAG, "handleCollision() called with: " + "s1 = [" + s1 + "], s2 = [" + s2 + "], collisionGroupMask = [" + collisionGroupMask + "]");
    }

    @Override
    public void render(GL11 gl) {
        super.render(gl);
        if (initialized) {
            onFightTurnListener.update();
        }
    }

    public SpriteInfo getPlayer() {
        return player;
    }

    public SpriteInfo getEnemy() {
        return enemy;
    }

    public void setOnFightTurnListener(FightSceneHandler onFightTurnListener) {
        this.onFightTurnListener = onFightTurnListener;
    }

    @Override
    public Fragment getHUD() {
        /* TODO ha harc közben meg akarom nyirni az inventory-t, az új fragment miatt meghal => singleton?
        * */

        return FightFragment.newInstance(enemy.getData().getMaxHealthPoint(), player.getData().getMaxHealthPoint(), player.getData().getActualHealthPoint());
    }
}
