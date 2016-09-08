package com.diploma.lilian.game.scene;

import android.content.Context;
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
import com.diploma.lilian.engine.io.SpriteDataParser;
import com.diploma.lilian.game.provider.SpriteInfo;

import java.io.IOException;

import javax.microedition.khronos.opengles.GL11;

public class FightScene extends BaseScene implements OnCollisionListener {

    private static final String TAG = "FightScene";
    private static int MAIN_LAYER;
    private static int GROUND_LAYER;
    private SpriteInfo player;
    private SpriteInfo enemy;

    public FightScene(Context context, int surfaceWidth, int surfaceHeight, SpriteInfo player, SpriteInfo enemy) {
        super(context, surfaceWidth, surfaceHeight);
        this.player = player;
        this.enemy = enemy;
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
        try {
            IsoSprite playerIsoSprite;
            playerIsoSprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/"+player.getSprite().getName()+".xml"));
            playerIsoSprite.moveInPlot(0, surfaceHeight / 2 - playerIsoSprite.getHeight(), 0.0f);
            playerIsoSprite.setAnimation("right_move");
            playerIsoSprite.setAnimationStartFrame(0);

            playerIsoSprite.setMoveAnimationNames(new String[]{"left_move", "left_up_move", "up_move", "right_up_move",
                    "right_move", "right_down_move", "down_move", "left_down_move"});

            vp.addElement(playerIsoSprite, MAIN_LAYER);
            playerIsoSprite.moveInIso(1, 0, 0);
            playerIsoSprite.addCollisionType(1);
            collitionDetector.add(playerIsoSprite);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            IsoSprite mushroom;
            mushroom = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/mushroom.xml"));
            Log.d(TAG, "init: mushroom.getWidth(): " + mushroom.getWidth() + " mushroom.getHeight() " + mushroom.getHeight() +
                    " surfaceWidth " + surfaceWidth + " surfaceHeight " + surfaceHeight);
            mushroom.moveInPlot(surfaceWidth - 2 * mushroom.getWidth(), surfaceHeight / 2 - mushroom.getHeight(), 0.0f);
            mushroom.setAnimation("stand");
            mushroom.setAnimationStartFrame(0);

            mushroom.setMoveAnimationNames(new String[]{"stand"});

            vp.addElement(mushroom, MAIN_LAYER);
            mushroom.moveInIso(1, 0, 0);
            mushroom.addCollisionType(1);
            collitionDetector.add(mushroom);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            IsoSprite grass = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/grass.xml"));
            grass.setWidth(surfaceWidth);
            grass.setHeight(surfaceHeight);
            grass.moveInPlot(0, 0, 0);
            grass.setAnimation("copy");

            vp.addElement(grass, GROUND_LAYER);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    }

}
