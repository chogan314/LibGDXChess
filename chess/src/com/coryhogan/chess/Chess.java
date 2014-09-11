package com.coryhogan.chess;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.coryhogan.chess.screens.GameScreen;

public class Chess extends Game {
	public static final String VERSION = "0.0.0.01 Pre-Alpha";
	public static final String LOG = "Chess";

	FPSLogger log;

	@Override
	public void create() {
		log = new FPSLogger();
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		super.render();
		log.log();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
