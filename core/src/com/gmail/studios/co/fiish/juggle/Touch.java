package com.gmail.studios.co.fiish.juggle;

import com.badlogic.gdx.math.Vector2;

public class Touch {
    public Vector2 mPos;

    public Touch(float x, float y) {
        mPos = new Vector2(x, y);
    }

    public boolean isInside(Ball ball) {
        if (Math.pow(mPos.x - ball.mPos.x, 2) + Math.pow(mPos.y - ball.mPos.y, 2) <= Math.pow(ball.mRadius, 2)) {
            return true;
        }
        return false;
    }

}
