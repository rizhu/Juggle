package com.gmail.studios.co.fiish.juggle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

enum Mode {ONE, TWO, THREE}

public class JuggleGameScreen extends ScreenAdapter {
    public Mode mMode;

    public Array<Ball> mBalls;
    public Viewport mViewport;

    public JuggleGameInputHandler mInputHandler;
    public BallHandler mBallHandler;

    SpriteBatch mBatch;

    public JuggleGameScreen(Mode mode) {
        this.mMode = mode;
    }

    @Override
    public void show() {
        mViewport = new ExtendViewport(500.0f, 500.0f);
        mBatch = new SpriteBatch();
        switch (mMode) {
            case ONE:
                mBalls = new Array<Ball>(1);
                mBalls.add(new Ball(Type.RED, mViewport));
                break;
            case TWO:
                mBalls = new Array<Ball>(2);
                mBalls.add(new Ball(Type.RED, mViewport));
                mBalls.add(new Ball(Type.GREEN, mViewport));
                break;
            case THREE:
                mBalls = new Array<Ball>(3);
                mBalls.add(new Ball(Type.RED, mViewport));
                mBalls.add(new Ball(Type.GREEN, mViewport));
                mBalls.add(new Ball(Type.BLUE, mViewport));
                break;
        }

        mInputHandler = new JuggleGameInputHandler(mMode, mBalls, mViewport);
        Gdx.input.setInputProcessor(new GestureDetector(mInputHandler));
    }

    @Override
    public void render(float delta) {
        mViewport.apply(true);

        Gdx.gl20.glClearColor(1, 1, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mBatch.begin();
        for (int i = 0; i < mBalls.size; i++) {
            mBalls.get(i).update(delta);
            mBallHandler.collisionCheck();
            mBalls.get(i).draw(mBatch);
        }
        mBatch.end();
    }

    @Override
    public void dispose() {
        for (int i = 0; i < mBalls.size; i++) {
            mBalls.get(i).dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        mViewport.update(width, height, true);
        for (int i = 0; i < mBalls.size; i++) {
            mBalls.get(i).init();
        }

        mBallHandler = new BallHandler(mViewport, mBalls);
    }


}
