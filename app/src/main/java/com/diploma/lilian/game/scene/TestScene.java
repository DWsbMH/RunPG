package com.diploma.lilian.game.scene;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.diploma.lilian.engine.FogOfWar;
import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.engine.LineDrawer;
import com.diploma.lilian.engine.Map;
import com.diploma.lilian.engine.Point;
import com.diploma.lilian.engine.Transformer;
import com.diploma.lilian.engine.Vec3;
import com.diploma.lilian.engine.Viewport;
import com.diploma.lilian.engine.ai.IPathFinder;
import com.diploma.lilian.engine.ai.IsoGridPathFinder;
import com.diploma.lilian.engine.collition.CollisionDetector;
import com.diploma.lilian.engine.collition.OnCollisionListener;
import com.diploma.lilian.game.CollisionType;
import com.diploma.lilian.game.OnFightListener;
import com.diploma.lilian.game.provider.BaseSpriteProvider;
import com.diploma.lilian.game.provider.SpriteInfo;

import java.util.Collection;

import javax.microedition.khronos.opengles.GL11;


public class TestScene extends BaseScene implements OnClickListener, OnCollisionListener, OnGestureListener {

    private static final String TAG = "TestScene";

    public static int WORLD_WIDTH = 5000;
    public static int WORLD_HEIGHT = 5000;

    public static int GROUND_LAYER;
    public static int MAIN_LAYER;

    private Vec3 startPos;

    private GestureDetector motions;
    private OnFightListener onFightListener;

    private IsoSprite enemy;

    private BaseSpriteProvider spriteProvider;
    private SpriteInfo player;

    public TestScene(Context context, int surfaceWidth, int surfaceHeight) {
        super(context, surfaceWidth, surfaceHeight);
        motions = new GestureDetector(this);
    }

    @Override
    protected Viewport createViewport() {
        Viewport vp = new Viewport(0, 0, surfaceWidth, surfaceHeight, WORLD_WIDTH, WORLD_HEIGHT, 0.5f);

        GROUND_LAYER = vp.addLayer(new Map(1));
        MAIN_LAYER = vp.addLayer(new Map(50));

        return vp;
    }

    @Override
    protected CollisionDetector createCollisionDetector() {
        Vec3 mapCorner1 = new Vec3(0, 0, 0);
        Vec3 mapCorner2 = new Vec3(WORLD_WIDTH, WORLD_HEIGHT, 400);

        CollisionDetector collitionDetector = new CollisionDetector(mapCorner1, mapCorner2, new Vec3(40, 40, 40));
        collitionDetector.addOnCollisionListener(this);

        return collitionDetector;
    }

    @Override
    protected IPathFinder createPathFinder() {
        return new IsoGridPathFinder(WORLD_WIDTH, WORLD_HEIGHT, 50);
    }

    @Override
    protected FogOfWar createFogOfWar() {
        return null;
//		return new FogOfWar("fogtex", 128, 128, WORLD_WIDTH, WORLD_HEIGHT);
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

        player = spriteProvider.getPlayerSpriteInfo();
        if (player.getSprite() != null && player.getSprite().getCollisionTypeBitmap() != CollisionType.INVALID.getValue()) {
            collitionDetector.add(player.getSprite());
        }

        vp.addElement(player.getSprite(), spriteProvider.getPlayerSpriteInfo().getLayerType());
    }

    @Override
    public void update(long elapsedTime) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        Log.d(TAG, "onTouch() called with: " + "v = [" + v + "], e = [" + e + "]");

        motions.onTouchEvent(e);
        return true;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void handleCollision(IsoSprite s1, IsoSprite s2, int collisionGroupMask) {
        Log.w("COLLITION:TestScene", "COLLIDE!!!!! " + s1 + " : " + s2 + " mask: " + collisionGroupMask);

        if(!s1.equals(s2)) {
            player.getSprite().stopMove();
            if (player.getSprite().equals(s1)) {
                enemy = s2;
            } else {
                enemy = s1;
            }

            SpriteInfo enemySpriteInfo = getEnemySpriteInfo(enemy);

            onFightListener.fight(player, enemySpriteInfo);
        }
    }

    private SpriteInfo getEnemySpriteInfo(IsoSprite enemy) {
        Collection<SpriteInfo> spriteInfos = spriteProvider.getSpriteInfoCollection();
        for (SpriteInfo spriteInfo : spriteInfos) {
            if (enemy.equals(spriteInfo.getSprite())) {
                return spriteInfo;
            }
        }

        return null;
    }


    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        //playerIsoSprite.moveTo(e.getX() + vp.getX(), e.getY() + vp.getY(), playerIsoSprite.getZ(), 200);
        player.getSprite().movePathTo(pathFinder, (e.getX() / getZoom()) + vp.getX(), (e.getY() / getZoom()) + vp.getY(), 500);

        Log.e("TEST SCENE", "TAP: x: " + (e.getX() + vp.getX()) + " Y: " + (e.getY() + vp.getY()));
        startPos = player.getSprite().getCenter();

        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {

        if (Math.abs(distanceX) < 1.0f && Math.abs(distanceY) < 1.0f)
            return false;

        vp.move((int) (distanceX), (int) (distanceY));

        return true;
    }


    @Override
    public void render(GL11 gl) {
        super.render(gl);
        if (player != null) {
            Point a = Transformer.isoToPlot(player.getSprite().getIminX(), player.getSprite().getIminY());
            Point b = Transformer.isoToPlot(player.getSprite().getImaxX(), player.getSprite().getImaxY());

            LineDrawer.drawLine(gl, a.x, a.y, b.x, b.y);

            a = Transformer.isoToPlot(player.getSprite().getImaxX(), player.getSprite().getIminY());
            b = Transformer.isoToPlot(player.getSprite().getIminX(), player.getSprite().getImaxY());

            LineDrawer.drawLine(gl, a.x, a.y, b.x, b.y);

            Vec3[] s2Path = player.getSprite().getPath();
            if (s2Path != null) {
                Point p0, p1;
                for (int i = 0; i < s2Path.length - 1; i++) {
                    p0 = Transformer.isoToPlot(s2Path[i].x, s2Path[i].y);
                    p1 = Transformer.isoToPlot(s2Path[i + 1].x, s2Path[i + 1].y);
                    LineDrawer.drawLine(gl, p0.x, p0.y, p1.x, p1.y);
                }
            }

            if (startPos != null) {
                a = Transformer.isoToPlot(startPos.x - 10, startPos.y - 10);
                b = Transformer.isoToPlot(startPos.x + 10, startPos.y + 10);

                LineDrawer.drawLine(gl, a.x, a.y, b.x, b.y);

                a = Transformer.isoToPlot(startPos.x + 10, startPos.y - 10);
                b = Transformer.isoToPlot(startPos.x - 10, startPos.y + 10);

                LineDrawer.drawLine(gl, a.x, a.y, b.x, b.y);
            }
        }

    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    public void setOnFightListener(OnFightListener onFightListener) {
        this.onFightListener = onFightListener;
    }

    public void resetCollisionDetector() {
        collitionDetector.addOnCollisionListener(this);
    }

    public void removeEnemy() {
        if (enemy != null) {
            collitionDetector.remove(enemy);
            vp.removeElement(enemy);
            enemy = null;
        }
    }

    public void setSpriteProvider(BaseSpriteProvider spriteProvider) {
        this.spriteProvider = spriteProvider;
    }

    public SpriteInfo getPlayer() {
        return player;
    }
}