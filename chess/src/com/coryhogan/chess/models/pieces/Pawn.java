package com.coryhogan.chess.models.pieces;

import com.badlogic.gdx.math.Vector2;
import com.coryhogan.chess.managers.EnPassantManager;
import com.coryhogan.chess.models.Piece;

public class Pawn extends Piece {
	//white move and attack patterns
	private static final Vector2 WHITE_MOVE_A = new Vector2(0, 1);
	private static final Vector2 WHITE_MOVE_B = new Vector2(0, 2);
	private static final Vector2 WHITE_ATTACK_A = new Vector2(1, 1);
	private static final Vector2 WHITE_ATTACK_B = new Vector2(-1, 1);
	
	//black move and attack patterns
	private static final Vector2 BLACK_MOVE_A = new Vector2(0, -1);
	private static final Vector2 BLACK_MOVE_B = new Vector2(0, -2);
	private static final Vector2 BLACK_ATTACK_A = new Vector2(1, -1);
	private static final Vector2 BLACK_ATTACK_B = new Vector2(-1, -1);
	
	private Vector2 doubleMovePos = null;
	private Vector2 singleMovePos = null;
	
	public Pawn(Color color) {
		super(color);
		type = Type.PAWN;
	}
	
	@Override
	public void calcValidMoves() {
		validMoves.clear();
		if(color == Color.WHITE) {
			calcWhiteMoves();
		} else {
			calcBlackMoves();
		}
	}
	
	private void calcWhiteMoves() {
		doubleMovePos = null;
		singleMovePos = null;
		Vector2 target = home().add(WHITE_MOVE_A);
		singleMovePos = target;
		addMove(target);
		
		if(!hasMoved) {
			target = home().add(WHITE_MOVE_B);
			doubleMovePos = target;
			addMove(target);
		}
	}
	
	private void calcBlackMoves() {
		doubleMovePos = null;
		singleMovePos = null;
		Vector2 target = home().add(BLACK_MOVE_A);
		singleMovePos = target;
		addMove(target);
		
		if(!hasMoved) {
			target = home().add(BLACK_MOVE_B);
			doubleMovePos = target;
			addMove(target);
		}
	}

	@Override
	public void calcValidAttacks() {
		validAttacks.clear();
		if(color == Color.WHITE) {
			calcWhiteAttacks();
		} else {
			calcBlackAttacks();
		}
	}
	
	private void calcWhiteAttacks() {
		Vector2 target = home().add(WHITE_ATTACK_A);
		addAttack(target);
		
		target = home().add(WHITE_ATTACK_B);
		addAttack(target);
	}
	
	private void calcBlackAttacks() {
		Vector2 target = home().add(BLACK_ATTACK_A);
		addAttack(target);
		
		target = home().add(BLACK_ATTACK_B);
		addAttack(target);
	}
	
	@Override
	public void tryAlpha(Vector2 target) {
		if(EnPassantManager.enPassant(this, board.findTileByLocation(target))) {
			System.out.println("returning...");
			return;
		}
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
			if(target.equals(doubleMovePos)) {
				EnPassantManager.setShadow(board.findTileByLocation(singleMovePos), this);
			} else {
				EnPassantManager.setNullShadow();
			}
			return;
		}
		resetPosition();
	}
}
