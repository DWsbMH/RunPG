package com.diploma.lilian.game.scene;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.diploma.lilian.database.entity.Player;
import com.diploma.lilian.engine.FogOfWar;
import com.diploma.lilian.engine.GameScene;
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
import com.diploma.lilian.engine.io.SpriteDataParser;
import com.diploma.lilian.game.OnFightListener;
import com.diploma.lilian.game.OnPlayerListener;
import com.diploma.lilian.game.entity.Enemy;

import java.io.IOException;

import javax.microedition.khronos.opengles.GL11;


public class TestScene extends GameScene implements OnClickListener, OnCollisionListener, OnGestureListener {

    private static final String TAG = "TestScene";

    private static int worldWidth = 5000;
    private static int worldHeight = 5000;

    private int GROUND_LAYER;
    private int MAIN_LAYER;

    private IsoSprite playerIsoSprite, grass;
    private IsoSprite mushroom;
    private Vec3 startPos;

    private GestureDetector motions;
    private OnPlayerListener onPlayerListener;
    private OnFightListener onFightListener;

    private com.diploma.lilian.game.entity.Player player;

    public TestScene(Context context, int surfaceWidth, int surfaceHeight, Player data) {
        super(context, surfaceWidth, surfaceHeight);
        player.setData(data);
        motions = new GestureDetector(this);
    }

    @Override
    protected Viewport createViewport() {
        Viewport vp = new Viewport(0, 0, surfaceWidth, surfaceHeight, worldWidth, worldHeight, 0.5f);

        GROUND_LAYER = vp.addLayer(new Map(1));
        MAIN_LAYER = vp.addLayer(new Map(50));

        return vp;
    }

    @Override
    protected CollisionDetector createCollisionDetector() {
        Vec3 mapCorner1 = new Vec3(0, 0, 0);
        Vec3 mapCorner2 = new Vec3(worldWidth, worldHeight, 400);

        CollisionDetector collitionDetector = new CollisionDetector(mapCorner1, mapCorner2, new Vec3(40, 40, 40));
        collitionDetector.addOnCollisionListener(this);

        return collitionDetector;
    }

    @Override
    protected IPathFinder createPathFinder() {
        return new IsoGridPathFinder(worldWidth, worldHeight, 50);
    }

    @Override
    protected FogOfWar createFogOfWar() {
        return null;
//		return new FogOfWar("fogtex", 128, 128, worldWidth, worldHeight);
    }

    @Override
    public void init(Context context) {
        player = new com.diploma.lilian.game.entity.Player();

        try {
            playerIsoSprite = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/male_light_sprite.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        playerIsoSprite.moveInPlot(1200, 700, 0);
        playerIsoSprite.setAnimation("left_down_move");
        playerIsoSprite.setAnimationStartFrame(0);

        playerIsoSprite.setMoveAnimationNames(new String[]{"left_move", "left_up_move", "up_move", "right_up_move",
                "right_move", "right_down_move", "down_move", "left_down_move"});

        vp.addElement(playerIsoSprite, MAIN_LAYER);
        playerIsoSprite.moveInIso(1, 0, 0);
        playerIsoSprite.addCollisionType(1);
        collitionDetector.add(playerIsoSprite);

        player.setSprite(playerIsoSprite);

        try {
            mushroom = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/mushroom.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mushroom.moveInPlot(700, 700, 0);
        mushroom.setAnimation("stand");
        mushroom.setAnimationStartFrame(0);

        mushroom.setMoveAnimationNames(new String[]{"stand"});

        vp.addElement(mushroom, MAIN_LAYER);
        mushroom.moveInIso(1, 0, 0);
        mushroom.addCollisionType(1);
        collitionDetector.add(mushroom);


        try {
            grass = SpriteDataParser.loadIsoSprite(context.getAssets().open("sprites/grass.xml"));
            grass.setWidth(worldWidth);
            grass.setHeight(worldHeight);
            grass.moveInPlot(0, 0, 0);
            grass.setAnimation("copy");

            vp.addElement(grass, GROUND_LAYER);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
/*
        float yd;

        yd = 0;

        switch (v.getId()) {
            case R.id.t_button:
                yd -= 0.1f;
                break;
            case R.id.b_button:
                yd += 0.1f;
                break;
            case R.id.l_button:
                long start, end;

                start = System.currentTimeMillis();
                Vec3[] path = pathFinder.getPath(playerIsoSprite, 0, 0);
                end = System.currentTimeMillis();

                Log.i("TestScene", "Path time: " + (end - start) + "ms");
                break;
            case R.id.r_button:

                break;
        }

        setZoom(getZoom() + yd);
*/
    }

    @Override
    public void handleCollision(IsoSprite s1, IsoSprite s2, int collisionGroupMask) {
        Log.w("COLLITION:TestScene", "COLLIDE!!!!! " + s1 + " : " + s2 + " maks: " + collisionGroupMask);

        this.playerIsoSprite.stopMove();
        this.playerIsoSprite.stopAnimation();


        onFightListener.fight(player,new Enemy(mushroom));

    }


    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        //playerIsoSprite.moveTo(e.getX() + vp.getX(), e.getY() + vp.getY(), playerIsoSprite.getZ(), 200);
        playerIsoSprite.movePathTo(pathFinder, (e.getX() / getZoom()) + vp.getX(), (e.getY() / getZoom()) + vp.getY(), 200);

        Log.e("TEST SCENE", "TAP: x: " + (e.getX() + vp.getX()) + " Y: " + (e.getY() + vp.getY()));
        startPos = playerIsoSprite.getCenter();

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

        Point a = Transformer.isoToPlot(playerIsoSprite.getIminX(), playerIsoSprite.getIminY());
        Point b = Transformer.isoToPlot(playerIsoSprite.getImaxX(), playerIsoSprite.getImaxY());

        LineDrawer.drawLine(gl, a.x, a.y, b.x, b.y);

        a = Transformer.isoToPlot(playerIsoSprite.getImaxX(), playerIsoSprite.getIminY());
        b = Transformer.isoToPlot(playerIsoSprite.getIminX(), playerIsoSprite.getImaxY());

        LineDrawer.drawLine(gl, a.x, a.y, b.x, b.y);

        Vec3[] s2Path = playerIsoSprite.getPath();
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

    public void setOnPlayerListener(OnPlayerListener onPlayerListener){
        this.onPlayerListener = onPlayerListener;
    }

    public void setOnFightListener(OnFightListener onFightListener) {
        this.onFightListener = onFightListener;
    }

    public com.diploma.lilian.game.entity.Player getPlayer() {
        return player;
    }

    public void resetCollisionDetector() {
        collitionDetector.addOnCollisionListener(this);
    }

    public void removeEnemy(Enemy enemy) {
        collitionDetector.remove(enemy.getSprite());
        vp.removeElement(enemy.getSprite());
    }
}