package com.gmail.studios.co.fiish.juggle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import java.util.HashMap;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class JuggleGameInputHandler extends InputAdapter {
    public Mode mMode;

    public Array<Ball> mBalls;
    //public Array<Touch> mTouches;
    public HashMap<Integer, Touch> mTouches;

    public Viewport mViewport;

    public JuggleGameInputHandler(Mode mode, Array<Ball> balls, Viewport viewport) {
        this.mMode = mode;
        this.mBalls = balls;
        this.mViewport = viewport;
        //mTouches = new Array<Touch>();
        mTouches = new HashMap<Integer, Touch>();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Touch touch = new Touch(screenX, screenY, mViewport);
        for (Ball ball : mBalls) {
            if (touch.isInside(ball)) {
                touch.mBall = ball;
                touch.mStart = TimeUtils.millis();
                mTouches.put(pointer, touch);
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (mTouches.containsKey(pointer)) {
            Touch touch = mTouches.get(pointer);
            touch.mElapsedTime = ((float) (TimeUtils.millis() - touch.mStart)) / 1000;
            if (touch.mElapsedTime < 0.15) {
                touch.mTouchDist = new Vector2(screenX - touch.mTouchPos.x, screenY - touch.mTouchPos.y);
                float velocityX = touch.mTouchDist.x / touch.mElapsedTime;
                float velocityY = touch.mTouchDist.y / touch.mElapsedTime;
                //Vector2 velocity = new Vector2(velocityX, velocityY);
                //Gdx.app.log("Velocity", "" + velocity.len());
                touch.mBall.mVelocity.x += velocityX;
                touch.mBall.mVelocity.y -= velocityY;
            }
            mTouches.remove(pointer);
        }
        return true;
    }

    /*
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        mTouches.add(new Touch(x, y, mViewport));
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        for (int i = 0; i < mTouches.size; i++) {
            for (Ball ball : mBalls) {
                if (mTouches.get(i).isInside(ball)) {
                    ball.mVelocity.x += velocityX;
                    ball.mVelocity.y -= velocityY;
                    mTouches.removeIndex(i);
                    return true;
                }
            }
        }
        return true;
    }
    */
}
