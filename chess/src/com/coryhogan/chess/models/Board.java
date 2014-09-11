package com.coryhogan.chess.models;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.coryhogan.chess.models.pieces.Bishop;
import com.coryhogan.chess.models.pieces.King;
import com.coryhogan.chess.models.pieces.Knight;
import com.coryhogan.chess.models.pieces.Pawn;
import com.coryhogan.chess.models.pieces.Queen;
import com.coryhogan.chess.models.pieces.Rook;

public class Board {
	public static final int CHESS_BOARD_WIDTH = 8;
	public static final int CHESS_BOARD_HEIGHT = 8;
	
	public int width, height;
	
	public ArrayList<Tile> tiles;
	public ArrayList<Piece> pieces;
	public Piece activePiece = null;
	
	
	
	public Board() {
		this(CHESS_BOARD_WIDTH, CHESS_BOARD_HEIGHT);
	}
	
	public Board(int width, int height) {
		tiles = new ArrayList<Tile>();
		pieces = new ArrayList<Piece>();
		this.width = width;
		this.height = height;
		buildBoard();
	}
	
	
	private void buildBoard() {
		boolean white = true;
		for(int y = 0; y < height; y++) {
			white = !white;
			for(int x = 0; x < width; x++) {
				if(white) {
					tiles.add(new Tile(Tile.Color.WHITE, x, y));
				} else {
					tiles.add(new Tile(Tile.Color.BLACK, x, y));
				}
				white = !white;
			}
		}
	}
	
	
	//checks to see if board contains location
	public boolean contains(float x, float y) {
		if(x < 0 || y < 0) {
			return false;
		}
		if(x >= width || y >= height) {
			return false;
		}
		
		return true;
	}
	public boolean contains(Vector2 location) {
		return contains(location.x, location.y);
	}
	
	
	//finds tiles on board
	public Tile findTileByLocation(float x, float y) {
		if(contains(x, y)) {
			int index = width * (int) y + (int) x;
			return tiles.get(index);
		} else {
			return null;
		}
	}
	public Tile findTileByLocation(Vector2 location) {
		return findTileByLocation(location.x, location.y);
	}
	
	
	//place piece on board
	public void placePiece(Piece piece, float x, float y) {
		findTileByLocation(x, y).setPiece(piece);
		pieces.add(piece);
	}
	
	
	//remove piece from board
	public void removePiece(Piece piece) {
		piece.tile.removePiece();
		pieces.remove(piece);
	}
	
	
	//moves piece on board
	public void movePiece(Piece piece, float x, float y) {
		piece.tile.removePiece();
		findTileByLocation(x, y).setPiece(piece);
	}
	
	
	//attacks with piece
	public void attackWithPiece(Piece piece, float x, float y) {
		pieces.remove(findTileByLocation(x, y).piece);
		findTileByLocation(x, y).removePiece();
		movePiece(piece, x, y);
	}
	
	
	
	public void setChessBoard() {
		new Pawn(Piece.Color.WHITE).place(this, 0, 1);
		new Pawn(Piece.Color.WHITE).place(this, 1, 1);
		new Pawn(Piece.Color.WHITE).place(this, 2, 1);
		new Pawn(Piece.Color.WHITE).place(this, 3, 1);
		new Pawn(Piece.Color.WHITE).place(this, 4, 1);
		new Pawn(Piece.Color.WHITE).place(this, 5, 1);
		new Pawn(Piece.Color.WHITE).place(this, 6, 1);
		new Pawn(Piece.Color.WHITE).place(this, 7, 1);
		
		new Knight(Piece.Color.WHITE).place(this, 1, 0);
		new Knight(Piece.Color.WHITE).place(this, 6, 0);
		
		new Bishop(Piece.Color.WHITE).place(this, 2, 0);
		new Bishop(Piece.Color.WHITE).place(this, 5, 0);
		
		new Rook(Piece.Color.WHITE).place(this, 0, 0);
		new Rook(Piece.Color.WHITE).place(this, 7, 0);
		
		new Queen(Piece.Color.WHITE).place(this, 3, 0);
	
		new King(Piece.Color.WHITE).place(this, 4, 0);
		
		
		
		new Pawn(Piece.Color.BLACK).place(this, 0, 6);
		new Pawn(Piece.Color.BLACK).place(this, 1, 6);
		new Pawn(Piece.Color.BLACK).place(this, 2, 6);
		new Pawn(Piece.Color.BLACK).place(this, 3, 6);
		new Pawn(Piece.Color.BLACK).place(this, 4, 6);
		new Pawn(Piece.Color.BLACK).place(this, 5, 6);
		new Pawn(Piece.Color.BLACK).place(this, 6, 6);
		new Pawn(Piece.Color.BLACK).place(this, 7, 6);
		
		new Knight(Piece.Color.BLACK).place(this, 1, 7);
		new Knight(Piece.Color.BLACK).place(this, 6, 7);
		
		new Bishop(Piece.Color.BLACK).place(this, 2, 7);
		new Bishop(Piece.Color.BLACK).place(this, 5, 7);
		
		new Rook(Piece.Color.BLACK).place(this, 0, 7);
		new Rook(Piece.Color.BLACK).place(this, 7, 7);
		
		new Queen(Piece.Color.BLACK).place(this, 4, 7);
		
		new King(Piece.Color.BLACK).place(this, 3, 7);
	}
}
