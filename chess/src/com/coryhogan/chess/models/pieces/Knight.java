package com.coryhogan.chess.models.pieces;

import com.badlogic.gdx.math.Vector2;
import com.coryhogan.chess.models.Piece;


public class Knight extends Piece{
	private static final Vector2 MOVE_A = new Vector2(1, 2);
	private static final Vector2 MOVE_B = new Vector2(2, 1);
	private static final Vector2 MOVE_C = new Vector2(-1, 2);
	private static final Vector2 MOVE_D = new Vector2(-2, 1);
	private static final Vector2 MOVE_E = new Vector2(-1, -2);
	private static final Vector2 MOVE_F = new Vector2(-2, -1);
	private static final Vector2 MOVE_G = new Vector2(1, -2);
	private static final Vector2 MOVE_H = new Vector2(2, -1);
	
	public Knight(Color color) {
		super(color);
		type = Type.KNIGHT;
	}

	@Override
	public void calcValidMoves() {
		validMoves.clear();
		
		Vector2 target = home().add(MOVE_A);
		addMove(target);
		target = home().add(MOVE_B);
		addMove(target);
		target = home().add(MOVE_C);
		addMove(target);
		target = home().add(MOVE_D);
		addMove(target);
		target = home().add(MOVE_E);
		addMove(target);
		target = home().add(MOVE_F);
		addMove(target);
		target = home().add(MOVE_G);
		addMove(target);
		target = home().add(MOVE_H);
		addMove(target);
	}

	@Override
	public void calcValidAttacks() {
		validAttacks.clear();
		
		Vector2 target = home().add(MOVE_A);
		addAttack(target);
		target = home().add(MOVE_B);
		addAttack(target);
		target = home().add(MOVE_C);
		addAttack(target);
		target = home().add(MOVE_D);
		addAttack(target);
		target = home().add(MOVE_E);
		addAttack(target);
		target = home().add(MOVE_F);
		addAttack(target);
		target = home().add(MOVE_G);
		addAttack(target);
		target = home().add(MOVE_H);
		addAttack(target);
	}
}
