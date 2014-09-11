package com.coryhogan.chess.models.pieces;

import com.badlogic.gdx.math.Vector2;
import com.coryhogan.chess.managers.CastleManager;
import com.coryhogan.chess.models.Piece;

public class King extends Piece {
	private static final Vector2 MOVE_A = new Vector2(1, 1);
	private static final Vector2 MOVE_B = new Vector2(1, -1);
	private static final Vector2 MOVE_C = new Vector2(-1, -1);
	private static final Vector2 MOVE_D = new Vector2(-1, 1);
	private static final Vector2 MOVE_E = new Vector2(1, 0);
	private static final Vector2 MOVE_F = new Vector2(0, 1);
	private static final Vector2 MOVE_G = new Vector2(-1, 0);
	private static final Vector2 MOVE_H = new Vector2(0, -1);

	public King(Color color) {
		super(color);
		type = Type.KING;
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
	
	@Override
	public void tryAlpha(Vector2 target) {
		if(CastleManager.castle(board, this, board.findTileByLocation(target).piece)) {
			return;
		}
		super.tryAlpha(target);
	}

}
