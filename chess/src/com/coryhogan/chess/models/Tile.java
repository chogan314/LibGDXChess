package com.coryhogan.chess.models;

import com.badlogic.gdx.math.Vector2;

public class Tile {
	public enum Color {
		WHITE, BLACK
	}
	
	public Color color;
	
	public Vector2 location;
	public boolean isOccupied = false;
	public Piece piece = null;
	
	
	
	public Tile(Color color, float x, float y) {
		this.color = color;
		location = new Vector2(x, y);
	}
	
	
	public void setPiece(Piece piece) {
		piece.tile = this;
		this.piece = piece;
		piece.position.set(location);
		isOccupied = true;
	}
	
	
	public void removePiece() {
		piece.tile = null;
		piece = null;
		isOccupied = false;
	}
}
