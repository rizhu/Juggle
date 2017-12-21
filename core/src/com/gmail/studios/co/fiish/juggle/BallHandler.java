package com.gmail.studios.co.fiish.juggle;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BallHandler {
    public Array<Ball> mBalls;
    public Viewport mViewport;

    public BallHandler(Viewport viewport, Array<Ball> balls) {
        this.mViewport = viewport;
        this.mBalls = balls;
    }

    public void collisionCheck() {
        if (mBalls.size > 1) {
            for (int i = 0; i < mBalls.size / 2; i++) {
                for (int j = mBalls.size / 2; j < mBalls.size; j++) {
                    if (mBalls.get(i).mPos.dst(mBalls.get(j).mPos) < 2 * mBalls.get(i).mRadius) {
                        mBalls.get(j).mVelocity.x = mBalls.get(i).mVelocity.x;
                        mBalls.get(j).mVelocity.y = mBalls.get(i).mVelocity.y;
                        mBalls.get(i).mVelocity.x = 0;
                        mBalls.get(i).mVelocity.y = 0;
                    }
                }
            }
        }
        for (Ball ball : mBalls) {
            if (ball.mPos.x - ball.mRadius < 0) {
                ball.mPos.x = ball.mRadius;
                ball.mVelocity.x = -ball.mVelocity.x;
            }
            if (ball.mPos.y - ball.mRadius < 0) {
                ball.mPos.y = ball.mRadius;
                ball.mVelocity.y = -ball.mVelocity.y;
            }
            if (ball.mPos.x + ball.mRadius > mViewport.getScreenWidth()) {
                ball.mPos.x = mViewport.getScreenWidth() - ball.mRadius;
                ball.mVelocity.x = -ball.mVelocity.x;
            }
            if (ball.mPos.y + ball.mRadius > mViewport.getScreenHeight()) {
                ball.mPos.y = mViewport.getScreenHeight() - ball.mRadius;
                ball.mVelocity.y = -ball.mVelocity.y;
            }
        }
    }

}
