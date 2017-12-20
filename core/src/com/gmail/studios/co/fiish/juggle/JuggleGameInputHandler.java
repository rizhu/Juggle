package com.gmail.studios.co.fiish.juggle;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.Array;

public class JuggleGameInputHandler extends GestureDetector.GestureAdapter {
    public Mode mMode;

    public Array<Ball> mBalls;
    public Array<Touch> mTouches;

    public JuggleGameInputHandler(Mode mode, Array<Ball> balls) {
        this.mMode = mode;
        this.mBalls = balls;
        mTouches = new Array<Touch>();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        mTouches.add(new Touch(x, y));
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        for (int i = 0; i < mTouches.size; i++) {
            for (Ball ball : mBalls) {
                if (mTouches.get(i).isInside(ball)) {
                    ball.mVelocity.x += velocityX;
                    ball.mVelocity.y += velocityY;
                    mTouches.removeIndex(i);
                    return true;
                }
            }
        }
        return true;
    }
}
