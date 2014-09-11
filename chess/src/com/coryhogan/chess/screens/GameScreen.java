package com.coryhogan.chess.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.coryhogan.chess.assets.Assets;
import com.coryhogan.chess.input.GameScreenInputHandler;
import com.coryhogan.chess.models.Board;
import com.coryhogan.chess.models.Piece;
import com.coryhogan.chess.models.Tile;

public class GameScreen extends AbstractScreen {
	public static final int PIXELS_PER_METER = 40;
	
	float width, height;	
	public OrthographicCamera cam;
	SpriteBatch batch;
	
	public Board board;

	public GameScreen(Game game) {
		super(game);
		
		width = Gdx.graphics.getWidth() / PIXELS_PER_METER;
		height = Gdx.graphics.getHeight() / PIXELS_PER_METER;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		cam.update();
		
		batch = new SpriteBatch();
		
		Assets.init();
		
		board = new Board();
		board.setChessBoard();
		
		Gdx.input.setInputProcessor(new GameScreenInputHandler(this));
	}
	
	@Override
	public void update(float delta) {
		
	}

	@Override
	public void draw(float exterpolation) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		batch.begin();
		for(Tile tile : board.tiles) {
			if(tile.color == Tile.Color.WHITE) {
				batch.draw(Assets.whiteTile, tile.location.x, tile.location.y, 1, 1);
			} else if (tile.color == Tile.Color.BLACK) {
				batch.draw(Assets.blackTile, tile.location.x, tile.location.y, 1, 1);
			}
		}
		
		for(Piece piece : board.pieces) {
			drawPiece(piece);
		}
		
		if(board.activePiece != null) {
			drawPiece(board.activePiece);
		}
		
		batch.end();
	}
	
	private void drawPiece(Piece piece) {
		if(piece.color == Piece.Color.WHITE) {
			switch(piece.type) {
				case PAWN:
					batch.draw(Assets.whitePawn, piece.position.x, piece.position.y, 1, 1);
					break;
				case KNIGHT:
					batch.draw(Assets.whiteKnight, piece.position.x, piece.position.y, 1, 1);
					break;
				case BISHOP:
					batch.draw(Assets.whiteBishop, piece.position.x, piece.position.y, 1, 1);
					break;
				case ROOK:
					batch.draw(Assets.whiteRook, piece.position.x, piece.position.y, 1, 1);
					break;
				case QUEEN:
					batch.draw(Assets.whiteQueen, piece.position.x, piece.position.y, 1, 1);
					break;
				case KING:
					batch.draw(Assets.whiteKing, piece.position.x, piece.position.y, 1, 1);
					break;
				default:
					break;		
			}
		} else if (piece.color == Piece.Color.BLACK) {
			switch(piece.type) {
				case PAWN:
					batch.draw(Assets.blackPawn, piece.position.x, piece.position.y, 1, 1);
					break;
				case KNIGHT:
					batch.draw(Assets.blackKnight, piece.position.x, piece.position.y, 1, 1);
					break;
				case BISHOP:
					batch.draw(Assets.blackBishop, piece.position.x, piece.position.y, 1, 1);
					break;
				case ROOK:
					batch.draw(Assets.blackRook, piece.position.x, piece.position.y, 1, 1);
					break;
				case QUEEN:
					batch.draw(Assets.blackQueen, piece.position.x, piece.position.y, 1, 1);
					break;
				case KING:
					batch.draw(Assets.blackKing, piece.position.x, piece.position.y, 1, 1);
					break;
				default:
					break;					
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		Assets.dispose();
	}
}
