package com.coryhogan.chess.managers;

import com.badlogic.gdx.math.Vector2;
import com.coryhogan.chess.models.Piece;
import com.coryhogan.chess.models.Tile;

public class EnPassantManager {
	private static final Vector2 MOVE_A = new Vector2(1, 1);
	private static final Vector2 MOVE_B = new Vector2(-1, 1);
	private static final Vector2 MOVE_C = new Vector2(-1, -1);
	private static final Vector2 MOVE_D = new Vector2(1, -1);
	
	private static Tile shadowTile;
	private static Piece shadowedPiece;
	
	public static void setShadow(Tile shadowTile, Piece shadowedPiece) {
		if(shadowedPiece.type == Piece.Type.PAWN) {
			EnPassantManager.shadowTile = shadowTile;
			EnPassantManager.shadowedPiece = shadowedPiece;
		} else {
			EnPassantManager.shadowTile = null;
			EnPassantManager.shadowedPiece = null;
		}
	}
	
	public static void setNullShadow() {
		EnPassantManager.shadowTile = null;
		EnPassantManager.shadowedPiece = null;
	}
	
	public static boolean enPassant(Piece source, Tile dest) {
		if(shadowedPiece == null || shadowTile == null) {
			return false;
		}
		
		if(source == null || dest == null) {
			return false;
		}
		
		if(!source.isEnemy(shadowedPiece)) {
			return false;
		}
		
		if(!dest.equals(shadowTile)) {
			return false;
		}
		
		if(source.color == Piece.Color.WHITE) {
			
			Vector2 targetA = source.home().add(MOVE_A);
			Vector2 targetB = source.home().add(MOVE_B);
			
			if(!targetA.epsilonEquals(dest.location,  0.01f) && !targetB.epsilonEquals(dest.location, 0.01f)) {
				return false;
			}
			
			if(targetA.epsilonEquals(dest.location, 0.01f)) {
				source.moveTo(targetA);
				shadowedPiece.remove();
				EnPassantManager.setNullShadow();
				return true;
			}
			
			if(targetB.epsilonEquals(dest.location, 0.01f)) {
				source.moveTo(targetB);
				shadowedPiece.remove();
				EnPassantManager.setNullShadow();
				return true;
			}
			
		} else if(source.color == Piece.Color.BLACK) {
			
			Vector2 targetC = source.home().add(MOVE_C);
			Vector2 targetD = source.home().add(MOVE_D);
			
			if(!targetC.epsilonEquals(dest.location,  0.01f) && !targetD.epsilonEquals(dest.location, 0.01f)) {
				return false;
			}
			
			if(targetC.epsilonEquals(dest.location, 0.01f)) {
				source.moveTo(targetC);
				shadowedPiece.remove();
				EnPassantManager.setNullShadow();
				return true;
			}
			
			if(targetD.epsilonEquals(dest.location, 0.01f)) {
				source.moveTo(targetD);
				shadowedPiece.remove();
				EnPassantManager.setNullShadow();
				return true;
			}
		}		
		
		return false;
	}
}
