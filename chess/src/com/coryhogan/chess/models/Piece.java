package com.coryhogan.chess.models;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.coryhogan.chess.managers.EnPassantManager;

public abstract class Piece {
	public enum Type {
		PAWN, BISHOP, KNIGHT, ROOK, QUEEN, KING
	}
	
	public enum Color {
		WHITE, BLACK
	}
	
	public Type type;
	public Color color;
	
	public Board board = null;
	public Tile tile = null;
	public Vector2 position = new Vector2();
	public boolean hasMoved = false;
	
	public ArrayList<Vector2> validMoves = new ArrayList<Vector2>();
	public ArrayList<Vector2> validAttacks = new ArrayList<Vector2>();
	
	
	
	
	public Piece(Color color) {
		this.color = color;
	}
	
	
	//place piece on board
	public void place(Board board, float x, float y) {
		if(this.board == null) {
			if(!board.findTileByLocation(x, y).isOccupied) {
				this.board = board;
				board.placePiece(this, x, y);
			} else {
				System.err.println("CANNOT PLACE " + type.toString() + " AT " + x + ", " + y + " -- TILE IS OCCUPIED");
			}
		} else {
			System.err.println("CANNOT PLACE " + type.toString() + " -- PIECE ALREADY ON BOARD AT " + position.x + ", " + position.y);
		}
	}
	public void place(Board board, Vector2 position) {
		place(board, position.x, position.y);
	}
	
	
	//remove piece from board
	public void remove() {
		if(board != null) {
			board.removePiece(this);
			board = null;
		} else {
			System.err.println("CANNOT REMOVE " + type.toString() + " -- PIECE NOT ON BOARD");
		}
	}
	
	
	//move piece on board
	public void moveTo(float x, float y) {
		if(board != null) {
			if(!board.findTileByLocation(x, y).isOccupied) {
				board.movePiece(this, x, y);
			} else {
				System.err.println("CANNOT MOVE " + type.toString() + " TO " + x + ", " + y + " -- TILE IS OCCUPIED");
			}
		} else {
			System.err.println("CANNOT MOVE " + type.toString() + " -- PIECE NOT ON BOARD");
		}
	}
	public void moveTo(Vector2 location) {
		moveTo(location.x, location.y);
	}
	
	
	//attack to target tile
	public void attackTo(float x, float y) {
		if(board != null) {
			board.attackWithPiece(this, x, y);
		} else {
			System.err.println("CANNOT ATTACK WITH " + type.toString() + " -- PIECE NOT ON BOARD");
		}
	}
	public void attackTo(Vector2 location) {
		attackTo(location.x, location.y);
	}
	
	
	//checks to see if another piece is an enemy
	public boolean isEnemy(Piece otherPiece) {
		return color != otherPiece.color;
	}
	
	
	//snap back to holding tile's position
	public void resetPosition() {
		position.set(tile.location);
	}
	
	
	//check if can move to location
	public boolean canMoveTo(float x, float y) {
		if(board.contains(x, y)) {
			if(!board.findTileByLocation(x, y).isOccupied) {
				return true;
			}
		}		
		return false;
	}
	public boolean canMoveTo(Vector2 location) {
		return canMoveTo(location.x, location.y);
	}
	
	
	//check if can attack location
	public boolean canAttackTo(float x, float y) {
		if(board.contains(x, y)) {
			if(board.findTileByLocation(x, y).isOccupied &&
					board.findTileByLocation(x, y).piece.isEnemy(this)) {
				return true;
			}
		}
		return false;
	}
	public boolean canAttackTo(Vector2 location) {
		return canAttackTo(location.x, location.y);
	}
	
	
	public void move(Vector2 target) {
		if(validMoves.contains(target)) {
			moveTo(target);
			hasMoved = true;
		} else {
			resetPosition();
		}
		
		validMoves.clear();
	}
	
	
	public void attack(Vector2 target) {
		if(validAttacks.contains(target)) {
			attackTo(target);
			hasMoved = true;
		} else {
			resetPosition();
		}
		
		validAttacks.clear();
	}
	
	protected void addMove(Vector2 target) {	
		if(canMoveTo(target)) {
			validMoves.add(target);
		}
	}
	
	protected void addAttack(Vector2 target) {
		if(canAttackTo(target)) {
			validAttacks.add(target);
		}
	}
	
	public Vector2 home() {
		return tile.location.cpy();
	}
	
	public void tryMove(Vector2 target) {
		calcValidMoves();
		move(target);
	}	
	
	public void tryAttack(Vector2 target) {
		calcValidAttacks();
		attack(target);
	}
	
	public void tryAlpha(Vector2 target) {
		calcValidAttacks();
		if(validAttacks.contains(target)) {
			attackTo(target);
			hasMoved = true;
			EnPassantManager.setNullShadow();
			return;
		}
		calcValidMoves();
		if(validMoves.contains(target)) {
			moveTo(target);
			hasMoved = true;
			EnPassantManager.setNullShadow();
			return;
		}
		resetPosition();
	}
	
	public abstract void calcValidMoves();
	public abstract void calcValidAttacks();
}
