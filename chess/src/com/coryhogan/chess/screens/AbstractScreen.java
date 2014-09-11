package com.coryhogan.chess.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {
	private static final float SECONDS_PER_UPDATE = 1f / 60f;
	
	protected Game game;
	private float lag = 0.0f;
	
	public AbstractScreen(Game game) {
		this.game = game;
	}
	
	public abstract void update(float delta);
	public abstract void draw(float exterpolation);
	
	@Override
	public void render(float delta) {
		lag += delta;
		
		while(lag >= SECONDS_PER_UPDATE) {
			update(SECONDS_PER_UPDATE);
			lag -= SECONDS_PER_UPDATE;
		}
		
		draw(lag);
	}
}
