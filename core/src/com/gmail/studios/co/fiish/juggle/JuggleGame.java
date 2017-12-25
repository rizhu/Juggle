package com.gmail.studios.co.fiish.juggle;

import com.badlogic.gdx.Game;

public class JuggleGame extends Game {
	@Override
	public void create() {
		setScreen(new JuggleGameScreen(Mode.THREE));
	}
}
