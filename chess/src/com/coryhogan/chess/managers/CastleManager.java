package com.coryhogan.chess.managers;

import com.badlogic.gdx.math.Vector2;
import com.coryhogan.chess.models.Board;
import com.coryhogan.chess.models.Piece;
import com.coryhogan.chess.models.Tile;

public class CastleManager {
	private static final Vector2 MOVE_A = new Vector2(1, 0);
	private static final Vector2 MOVE_B = new Vector2(-1, 0);
	private static final Vector2 MOVE_C = new Vector2(0, 1);
	private static final Vector2 MOVE_D = new Vector2(0, -1);
	
	public static boolean castle(Board board, Piece source, Piece dest) {
		//return if one or more pieces don't exist
		if(source == null || dest == null) {
			return false;
		}
		
		//return if pieces not the same color
		if(source.isEnemy(dest)) {
			return false;
		}
		
		//return if the source is not a king of rook
		if(source.type != Piece.Type.KING && source.type != Piece.Type.ROOK) {
			return false;
		}
		
		//return if the source is a king and the dest is not a rook
		if(source.type == Piece.Type.KING && dest.type != Piece.Type.ROOK) {
			return false;
		}
		
		//return if the source is a rook and the dest is not a king
		if(source.type == Piece.Type.ROOK && dest.type != Piece.Type.KING) {
			return false;
		}
		
		//return if pieces are on different board
		if(!source.board.equals(dest.board)) {
			return false;
		}
		
		//return if either piece has moved
		if(source.hasMoved || dest.hasMoved) {
			return false;
		}
		
		//return if pieces are not in straight (non-diagonal) line
		if(source.tile.location.x != dest.tile.location.x &&
				source.tile.location.y != dest.tile.location.y) {
			return false;
		}
		
		
		//return if a piece is between source and dest
		if(source.tile.location.x == dest.tile.location.x) {
			Vector2 target = source.home();
			
			if(source.tile.location.y > dest.tile.location.y) {
				target.add(MOVE_D);
				while(target.y > dest.tile.location.y) {
					Tile targetTile = board.findTileByLocation(target);
					if(targetTile == null) {
						return false;
					}
					if(targetTile.isOccupied) {
						return false;
					}
					target.add(MOVE_D);
				}
			} else {
				target.add(MOVE_C);
				while(target.y < dest.tile.location.y) {
					Tile targetTile = board.findTileByLocation(target);
					if(targetTile == null) {
						return false;
					}
					if(targetTile.isOccupied) {
						return false;
					}
					target.add(MOVE_C);
				}
			}
			
		} else if (source.tile.location.y == dest.tile.location.y) {
			Vector2 target = source.home();
			
			if(source.tile.location.x > dest.tile.location.x) {
				target.add(MOVE_B);
				while(target.x > dest.tile.location.x) {
					Tile targetTile = board.findTileByLocation(target);
					if(targetTile == null) {
						return false;
					}
					if(targetTile.isOccupied) {
						return false;
					}
					target.add(MOVE_B);
				}
			} else {
				target.add(MOVE_A);
				while(target.x < dest.tile.location.x) {
					Tile targetTile = board.findTileByLocation(target);
					if(targetTile == null) {
						return false;
					}
					if(targetTile.isOccupied) {
						return false;
					}
					target.add(MOVE_A);
				}
			}
		}
		
		//swap source and dest -- return true
		Tile sourceTile = source.tile;
		Tile destTile = dest.tile;
		source.remove();
		dest.remove();
		source.place(board, destTile.location);
		dest.place(board, sourceTile.location);
		source.hasMoved = true;
		dest.hasMoved = true;
		
		return true;
	}
	
}
