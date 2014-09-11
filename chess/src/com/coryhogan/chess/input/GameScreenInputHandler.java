package com.coryhogan.chess.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.coryhogan.chess.models.Piece;
import com.coryhogan.chess.models.Tile;
import com.coryhogan.chess.screens.GameScreen;

public class GameScreenInputHandler implements InputProcessor {
	private Vector3 touchPos;
	private Vector2 touchPos2;
	private Vector2 lastTouchPos2;
	private Vector2 deltaTouchPos;
	
	private GameScreen screen;
	private OrthographicCamera cam;
	private Piece heldPiece = null;
	
	public GameScreenInputHandler(GameScreen screen) {
		this.screen = screen;
		cam = screen.cam;
		
		touchPos = new Vector3();
		touchPos2 = new Vector2();
		lastTouchPos2 = new Vector2();
		deltaTouchPos = new Vector2();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(pointer == 0 && button == 0) {
			cam.unproject(touchPos.set(screenX, screenY, 0));
			touchPos2.set(touchPos.x, touchPos.y);
			lastTouchPos2.set(touchPos2);
			
			Tile tile = screen.board.findTileByLocation(touchPos2);
			if(tile != null) {
				heldPiece = tile.piece;
				screen.board.activePiece = heldPiece;
			}
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(pointer == 0 && button == 0) {
			cam.unproject(touchPos.set(screenX, screenY, 0));
			touchPos2.set(touchPos.x, touchPos.y);
			
			if(heldPiece != null) {
				if(screen.board.contains(heldPiece.position.x + 0.5f, heldPiece.position.y + 0.5f)) {
					
					heldPiece.tryAlpha(screen.board.findTileByLocation(heldPiece.position.x + 0.5f, 
							heldPiece.position.y + 0.5f).location);
					
				} else {
					heldPiece.resetPosition();
				}
				heldPiece = null;
				screen.board.activePiece = heldPiece;
			}
			
		}
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(pointer == 0) {
			cam.unproject(touchPos.set(screenX, screenY, 0));
			touchPos2.set(touchPos.x, touchPos.y);
			deltaTouchPos.set(touchPos2.x - lastTouchPos2.x, touchPos2.y - lastTouchPos2.y);
			
			if(heldPiece != null) {
				heldPiece.position.add(deltaTouchPos);
			}
			
			lastTouchPos2.set(touchPos2);
		}
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
