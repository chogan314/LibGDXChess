package com.coryhogan.chess.models.pieces;

import com.badlogic.gdx.math.Vector2;
import com.coryhogan.chess.models.Piece;

public class Queen extends Piece {
	private static final Vector2 MOVE_A = new Vector2(1, 1);
	private static final Vector2 MOVE_B = new Vector2(1, -1);
	private static final Vector2 MOVE_C = new Vector2(-1, -1);
	private static final Vector2 MOVE_D = new Vector2(-1, 1);
	private static final Vector2 MOVE_E = new Vector2(1, 0);
	private static final Vector2 MOVE_F = new Vector2(0, 1);
	private static final Vector2 MOVE_G = new Vector2(-1, 0);
	private static final Vector2 MOVE_H = new Vector2(0, -1);

	public Queen(Color color) {
		super(color);
		type = Type.QUEEN;
	}

	@Override
	public void calcValidMoves() {
		validMoves.clear();
		
		Vector2 target = home().add(MOVE_A);
		while(canMoveTo(target)) {
			addMove(target.cpy());
			target.add(MOVE_A);
		}
		
		target = home().add(MOVE_B);
		while(canMoveTo(target)) {
			addMove(target.cpy());
			target.add(MOVE_B);
		}
		
		target = home().add(MOVE_C);
		while(canMoveTo(target)) {
			addMove(target.cpy());
			target.add(MOVE_C);
		}
		
		target = home().add(MOVE_D);
		while(canMoveTo(target)) {
			addMove(target.cpy());
			target.add(MOVE_D);
		}
		
		target = home().add(MOVE_E);
		while(canMoveTo(target)) {
			addMove(target.cpy());
			target.add(MOVE_E);
		}
		
		target = home().add(MOVE_F);
		while(canMoveTo(target)) {
			addMove(target.cpy());
			target.add(MOVE_F);
		}
		
		target = home().add(MOVE_G);
		while(canMoveTo(target)) {
			addMove(target.cpy());
			target.add(MOVE_G);
		}
		
		target = home().add(MOVE_H);
		while(canMoveTo(target)) {
			addMove(target.cpy());
			target.add(MOVE_H);
		}
	}

	@Override
	public void calcValidAttacks() {
		validAttacks.clear();
		
		Vector2 target = home().add(MOVE_A);
		while(canMoveTo(target)) {
			target.add(MOVE_A);
		}
		if(board.contains(target) && board.findTileByLocation(target).isOccupied &&
				board.findTileByLocation(target).piece.isEnemy(this)) {
			validAttacks.add(target);
		}
		
		target = home().add(MOVE_B);
		while(canMoveTo(target)) {
			target.add(MOVE_B);
		}
		if(board.contains(target) && board.findTileByLocation(target).isOccupied &&
				board.findTileByLocation(target).piece.isEnemy(this)) {
			validAttacks.add(target);
		}
		
		target = home().add(MOVE_C);
		while(canMoveTo(target)) {
			target.add(MOVE_C);
		}
		if(board.contains(target) && board.findTileByLocation(target).isOccupied &&
				board.findTileByLocation(target).piece.isEnemy(this)) {
			validAttacks.add(target);
		}
		
		target = home().add(MOVE_D);
		while(canMoveTo(target)) {
			target.add(MOVE_D);
		}
		if(board.contains(target) && board.findTileByLocation(target).isOccupied &&
				board.findTileByLocation(target).piece.isEnemy(this)) {
			validAttacks.add(target);
		}
		
		target = home().add(MOVE_E);
		while(canMoveTo(target)) {
			target.add(MOVE_E);
		}
		if(board.contains(target) && board.findTileByLocation(target).isOccupied &&
				board.findTileByLocation(target).piece.isEnemy(this)) {
			validAttacks.add(target);
		}
		
		target = home().add(MOVE_F);
		while(canMoveTo(target)) {
			target.add(MOVE_F);
		}
		if(board.contains(target) && board.findTileByLocation(target).isOccupied &&
				board.findTileByLocation(target).piece.isEnemy(this)) {
			validAttacks.add(target);
		}
		
		target = home().add(MOVE_G);
		while(canMoveTo(target)) {
			target.add(MOVE_G);
		}
		if(board.contains(target) && board.findTileByLocation(target).isOccupied &&
				board.findTileByLocation(target).piece.isEnemy(this)) {
			validAttacks.add(target);
		}
		
		target = home().add(MOVE_H);
		while(canMoveTo(target)) {
			target.add(MOVE_H);
		}
		if(board.contains(target) && board.findTileByLocation(target).isOccupied &&
				board.findTileByLocation(target).piece.isEnemy(this)) {
			validAttacks.add(target);
		}
	}

}
