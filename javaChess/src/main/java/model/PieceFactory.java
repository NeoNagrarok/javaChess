package model;

import java.util.HashMap;

import model.Game.Color;

public class PieceFactory
{
	static public void makePiece(Case pos)
	{
		int x = pos.getX();
		int y = pos.getY();
		HashMap<Integer,Color> colorPos = new HashMap<>();
		colorPos.put(Integer.valueOf(0), Color.BLACK);
		colorPos.put(Integer.valueOf(1), Color.BLACK);
		colorPos.put(Integer.valueOf(6), Color.WHITE);
		colorPos.put(Integer.valueOf(7), Color.WHITE);

		if (x == 0 || x == 7)
			switch (y) {
				case 0:
				case 7:
					pos.setPiece(new Rook(colorPos.get(Integer.valueOf(x))));
					break;
				case 1:
				case 6:
					pos.setPiece(new Knight(colorPos.get(Integer.valueOf(x))));
					break;
				case 2:
				case 5:
					pos.setPiece(new Bishop(colorPos.get(Integer.valueOf(x))));
					break;
				case 3:
					pos.setPiece(new Queen(colorPos.get(Integer.valueOf(x))));
					break;
				case 4:
					pos.setPiece(new King(colorPos.get(Integer.valueOf(x))));
					break;
			}
			else if (x == 1 || x == 6)
				pos.setPiece(new Pawn(colorPos.get(Integer.valueOf(x))));
	}

	static public Piece makePieceFromeId(Piece piece)
	{
		if (piece != null && piece.id != null)
			switch (piece.id) {
				case "r":
					return new Rook(piece);
				case "b":
					return new Bishop(piece);
				case "k":
					return new Knight(piece);
				case "Q":
					return new Queen(piece);
				case "K":
					return new King(piece);
				case "p":
					return new Pawn(piece);
			}
		return null;
	}
}
