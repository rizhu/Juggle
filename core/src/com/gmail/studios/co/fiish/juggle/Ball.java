package com.gmail.studios.co.fiish.juggle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.Viewport;

enum Type {RED, GREEN, BLUE}

public class Ball {
    public Type mType;

    public Vector2 mPos, mVelocity;
    public Texture mRed, mGreen, mBlue;
    public Sprite mSprite;
    public Viewport mViewport;

    public final float M_RADIUS = 20.0f;

    public Ball(Type type, Viewport viewport) {
        this.mType = type;
        this.mViewport = viewport;
    }

    public void init() {
        mPos = new Vector2(mViewport.getScreenWidth() / 2, mViewport.getScreenHeight() / 2);
        mVelocity = new Vector2();

        mRed = new Texture(Gdx.files.internal("redball.png"));
        mGreen = new Texture(Gdx.files.internal("greenball.png"));
        mBlue = new Texture(Gdx.files.internal("blueball.png"));

        switch (mType) {
            case RED: mSprite = new Sprite(mRed /*, (int) M_RADIUS * 2, (int) M_RADIUS * 2*/);
            break;
            case GREEN: mSprite = new Sprite(mGreen /*, (int) M_RADIUS * 2, (int) M_RADIUS * 2*/);
            break;
            case BLUE: mSprite = new Sprite(mBlue /*, (int) M_RADIUS * 2, (int) M_RADIUS * 2*/);
            break;
        }

        mSprite.setPosition(mPos.x - mSprite.getWidth() / 2, mPos.y - mSprite.getHeight() / 2);
    }

    public void dispose() {
        mRed.dispose();
        mGreen.dispose();
        mBlue.dispose();
    }

}
