package com.coryhogan.chess.assets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;


public class Assets {
	public static TextureAtlas atlas;
	public static AtlasRegion whiteTile;
	public static AtlasRegion blackTile;
	public static AtlasRegion whitePawn;
	public static AtlasRegion whiteKnight;
	public static AtlasRegion whiteBishop;
	public static AtlasRegion whiteRook;
	public static AtlasRegion whiteQueen;
	public static AtlasRegion whiteKing;
	public static AtlasRegion blackPawn;
	public static AtlasRegion blackKnight;
	public static AtlasRegion blackBishop;
	public static AtlasRegion blackRook;
	public static AtlasRegion blackQueen;
	public static AtlasRegion blackKing;
	
	public static void init() {
		atlas = new TextureAtlas("data/Chess.pack");
		whiteTile = atlas.findRegion("whiteTileTexture");
		blackTile = atlas.findRegion("blackTileTexture");
		whitePawn = atlas.findRegion("whitePawnTexture");
		whiteKnight = atlas.findRegion("whiteKnightTexture");
		whiteBishop = atlas.findRegion("whiteBishopTexture");
		whiteRook = atlas.findRegion("whiteRookTexture");
		whiteQueen = atlas.findRegion("whiteQueenTexture");
		whiteKing = atlas.findRegion("whiteKingTexture");
		blackPawn = atlas.findRegion("blackPawnTexture");
		blackKnight = atlas.findRegion("blackKnightTexture");
		blackBishop = atlas.findRegion("blackBishopTexture");
		blackRook = atlas.findRegion("blackRookTexture");
		blackQueen = atlas.findRegion("blackQueenTexture");
		blackKing = atlas.findRegion("blackKingTexture");
	}
	
	public static void dispose() {
		atlas.dispose();
	}
}
