package com.gmail.studios.co.fiish.juggle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Touch {
    public Vector2 mTouchPos;
    public Viewport mViewport;

    public Touch(float x, float y, Viewport viewport) {
        this.mTouchPos = new Vector2(x, y);
        this.mViewport = viewport;
    }

    public boolean isInside(Ball ball) {
        if (Math.pow(mTouchPos.x - ball.mPos.x, 2) + Math.pow((mViewport.getScreenHeight() - 1 - mTouchPos.y) - ball.mPos.y, 2) <= Math.pow(ball.mRadius, 2)) {
            return true;
        }
        return false;
    }

}
